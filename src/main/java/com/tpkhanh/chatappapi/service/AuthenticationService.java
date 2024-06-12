package com.tpkhanh.chatappapi.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tpkhanh.chatappapi.dto.request.*;
import com.tpkhanh.chatappapi.dto.response.AuthenticationResponse;
import com.tpkhanh.chatappapi.dto.response.IntrospectResponse;
import com.tpkhanh.chatappapi.enums.RoleEnum;
import com.tpkhanh.chatappapi.exception.AppException;
import com.tpkhanh.chatappapi.exception.ErrorCode;
import com.tpkhanh.chatappapi.model.Account;
import com.tpkhanh.chatappapi.model.InvalidatedToken;
import com.tpkhanh.chatappapi.model.Role;
import com.tpkhanh.chatappapi.model.User;
import com.tpkhanh.chatappapi.repository.*;
import com.tpkhanh.chatappapi.repository.httpclient.OutboundChatappClient;
import com.tpkhanh.chatappapi.repository.httpclient.OutboundUserClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.Normalizer;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationService {
    AccountRepository accountRepository;

    InvalidatedTokenRepository invalidatedTokenRepository;

    LogLockAccountRepository logLockAccountRepository;

    OutboundChatappClient outboundChatappClient;

    OutboundUserClient outboundUserClient;

    RoleRepository roleRepository;

    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNED_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    @NonFinal
    @Value("${outbound.chatapp.client-id}")
    protected String CLIENT_ID;

    @NonFinal
    @Value("${outbound.chatapp.client-secret}")
    protected String CLIENT_SECRET;

    @NonFinal
    @Value("${outbound.chatapp.redirect-uri}")
    protected String REDIRECT_URI;

    @NonFinal
    protected final String GRANT_TYPE = "authorization_code";

    public AuthenticationResponse outboundAuthenticate(String code){
        var response = outboundChatappClient.exchangeToken(ExchangeTokenRequest.builder()
                .code(code)
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .redirectUri(REDIRECT_URI)
                .grantType(GRANT_TYPE)
                .build());

        log.info("TOKEN RESPONSE {}", response);

        var userInfo = outboundUserClient.getUserInfo("json", response.getAccessToken());

        log.info("User Info {}", userInfo);

        Set<Role> roles = new HashSet<>();
        Role rolesUser = roleRepository.findById(RoleEnum.USER.name())
                .orElseThrow(() -> new RuntimeException("Role USER not found"));
        roles.add(rolesUser);

        var account = accountRepository.findByAccount(userInfo.getEmail()).orElseGet(() -> {
            Account newAccount = accountRepository.save(Account.builder()
                    .account(userInfo.getEmail())
                    .password("")
                    .dateTimeCreate(LocalDateTime.now())
                    .roles(roles)
                    .build());

            User newUser = userRepository.save(User.builder()
                    .idUser(generateUserId(userInfo.getFamilyName() + " " + userInfo.getGivenName()))
                    .avatar(userInfo.getPicture())
                    .name(userInfo.getFamilyName() + userInfo.getGivenName())
                    .lastTimeActive(LocalDateTime.now())
                    .stateActive(false)
                    .account(newAccount)
                    .build());
            userRepository.save(newUser);

            return newAccount;
        });

        var token = generateToken(account);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        boolean isValid = true;

        try {
            verifyToken(token, false);
        }
        catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        var account = accountRepository.findByAccount(request.getAccount())
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));

        boolean authenticated = passwordEncoder
                .matches(request.getPassword(), account.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        boolean checkExisted = logLockAccountRepository.existsByAccount_IdAccount(account.getIdAccount());

        if (checkExisted) {
            if (logLockAccountRepository.findFirstByAccount_IdAccountOrderByDateTimeLockDesc(account.getIdAccount()).getStateLock()) {
                throw new RuntimeException(logLockAccountRepository.findFirstByAccount_IdAccountOrderByDateTimeLockDesc(account.getIdAccount()).getReasonLock());
            }
        }

        var token = generateToken(account);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(), false);

            String jit = signToken.getJWTClaimsSet().getJWTID();

            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            log.info(String.valueOf(expiryTime));

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(jit)
                    .expiryDate(expiryTime)
                    .build();

            invalidatedTokenRepository.save(invalidatedToken);
        } catch (AppException exception) {
            log.info("Token already expired");
        }
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        var signJWT = verifyToken(request.getToken(), true);

        var jit = signJWT.getJWTClaimsSet().getJWTID();

        var expiryTime = signJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryDate(expiryTime)
                .build();

        invalidatedTokenRepository.save(invalidatedToken);

        var username = signJWT.getJWTClaimsSet().getSubject();

        var account = accountRepository.findByAccount(username)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        var token = generateToken(account);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNED_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh) ?
                new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                :
                signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date())))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    private String generateToken(Account account) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(account.getAccount())
                .issuer("https://chatappapi.tpkhanh.com/")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(account))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNED_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildScope(Account account) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(account.getRoles()))
            account.getRoles().forEach(role -> {
                stringJoiner.add(role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
            });

        return stringJoiner.toString();
    }

    public String generateUserId(String fullName) {
        String[] nameParts = fullName.split(" ");
        StringBuilder userIdBuilder = new StringBuilder();

        if (nameParts.length < 2) {
            // Trường hợp tên không đầy đủ, chỉ trả về tên nguyên gốc
            return fullName.toLowerCase();
        }

        for (int i = 0; i < nameParts.length - 1; i++) {
            userIdBuilder.append(Character.toLowerCase(nameParts[i].charAt(0))); // Chữ cái đầu viết thường
        }

        userIdBuilder.append("_");

        String lastName = nameParts[nameParts.length - 1];
        userIdBuilder.append(Character.toLowerCase(lastName.charAt(0))); // Chữ cái đầu của tên cuối cùng viết thường

        String normalizedLastName = Normalizer.normalize(lastName, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", ""); // Xóa các dấu
        userIdBuilder.append(normalizedLastName.toLowerCase().substring(1)); // Bỏ chữ cái đầu và viết thường phần còn lại của tên cuối cùng

        return userIdBuilder.toString();
    }
}
