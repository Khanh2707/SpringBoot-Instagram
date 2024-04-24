package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.request.AccountCreationRequest;
import com.tpkhanh.chatappapi.dto.request.AccountUpdateRequest;
import com.tpkhanh.chatappapi.dto.request.UserCreationRequest;
import com.tpkhanh.chatappapi.dto.request.UserUpdateInfoRequest;
import com.tpkhanh.chatappapi.dto.response.AccountResponse;
import com.tpkhanh.chatappapi.dto.response.UserResponse;
import com.tpkhanh.chatappapi.enums.RoleEnum;
import com.tpkhanh.chatappapi.exception.AppException;
import com.tpkhanh.chatappapi.exception.ErrorCode;
import com.tpkhanh.chatappapi.mapper.UserMapper;
import com.tpkhanh.chatappapi.model.Account;
import com.tpkhanh.chatappapi.model.Role;
import com.tpkhanh.chatappapi.model.User;
import com.tpkhanh.chatappapi.repository.AccountRepository;
import com.tpkhanh.chatappapi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {

    UserMapper userMapper;

    UserRepository userRepository;

    AccountRepository accountRepository;

    public UserResponse createUser(UserCreationRequest request) {

        if (userRepository.existsByIdUser(request.getIdUser()))
            throw new AppException(ErrorCode.ID_USER_EXISTED);

        User user = userMapper.toUser(request);

        user.setStateActive(false);
        user.setLastTimeActive(LocalDateTime.now());

        Account account = accountRepository.findById(request.getIdAccountUser()).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        user.setAccount(account);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse updateUserInfo(String userId, UserUpdateInfoRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.ID_USER_EXISTED));

        userMapper.updateUserInfo(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getUsersByKeyword(String keyword, String idUser) {
        return userRepository.findAll().stream()
                .filter(user -> {
                    if (!idUser.equals("null") && !idUser.isEmpty()) {
                        return user.getIdUser().contains(keyword) && !user.getIdUser().equals(idUser);
                    }
                    return user.getIdUser().contains(keyword);
                })
                .map(userMapper::toUserResponse)
                .toList();
    }
}
