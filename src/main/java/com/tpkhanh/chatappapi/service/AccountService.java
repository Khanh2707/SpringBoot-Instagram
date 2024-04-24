package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.request.AccountCreationRequest;
import com.tpkhanh.chatappapi.dto.request.AccountUpdatePasswordRequest;
import com.tpkhanh.chatappapi.dto.request.AccountUpdateRequest;
import com.tpkhanh.chatappapi.dto.response.AccountResponse;
import com.tpkhanh.chatappapi.enums.RoleEnum;
import com.tpkhanh.chatappapi.exception.AppException;
import com.tpkhanh.chatappapi.exception.ErrorCode;
import com.tpkhanh.chatappapi.mapper.AccountMapper;
import com.tpkhanh.chatappapi.model.Account;
import com.tpkhanh.chatappapi.model.Role;
import com.tpkhanh.chatappapi.repository.AccountRepository;
import com.tpkhanh.chatappapi.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountService {

    AccountRepository accountRepository;

    AccountMapper accountMapper;

    PasswordEncoder passwordEncoder;

    RoleRepository roleRepository;

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public List<AccountResponse> getAllAccounts() {
        log.info("in method getAllAccounts");

        return accountRepository.findAll().stream().map(accountMapper::toAccountResponse).toList();
    }

    @PostAuthorize("returnObject.account == authentication.name")
    public AccountResponse getAccountById(Integer id) {
        log.info("in method getAccountById");

        return accountMapper.toAccountResponse(accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND)));
    }

    public AccountResponse getMyAccount() {
        var context = SecurityContextHolder.getContext();
        String usernameAccount = context.getAuthentication().getName();

        Account account = accountRepository.findByAccount(usernameAccount).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));

        return accountMapper.toAccountResponse(account);
    }

    public AccountResponse createAccount(AccountCreationRequest request) {

        if (accountRepository.existsByAccount(request.getAccount()))
            throw new AppException(ErrorCode.ACCOUNT_EXISTED);

        Account account = accountMapper.toAccount(request);

        account.setPassword(passwordEncoder.encode(request.getPassword()));

        account.setDateTimeCreate(LocalDateTime.now());

        HashSet<Role> roles = new HashSet<>();
//        roles.add(Role.USER.name());

        Role rolesUser = roleRepository.findById(RoleEnum.USER.name())
                .orElseThrow(() -> new RuntimeException("Role USER not found"));
        roles.add(rolesUser);
        account.setRoles(roles);

        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    public AccountResponse updateAccount(Integer accountId, AccountUpdateRequest request) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));

        accountMapper.updateAccount(account, request);

        account.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        account.setRoles(new HashSet<>(roles));

        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    public AccountResponse updateAccountPassword(Integer accountId, AccountUpdatePasswordRequest request) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));

        log.info(request.getPassword());
        log.info(request.getCurrentPassword());
        if (passwordEncoder.matches(request.getCurrentPassword(), account.getPassword())) {
            accountMapper.updateAccountPassword(account, request);

            account.setPassword(passwordEncoder.encode(request.getPassword()));

            return accountMapper.toAccountResponse(accountRepository.save(account));
        }
        else {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }
    }

    public void deleteAccount(Integer accountId) {
        accountRepository.deleteById(accountId);
    }
}
