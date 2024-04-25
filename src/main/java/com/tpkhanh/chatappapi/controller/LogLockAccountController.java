package com.tpkhanh.chatappapi.controller;

import com.tpkhanh.chatappapi.dto.request.ApiResponse;
import com.tpkhanh.chatappapi.dto.request.LogLockAccountCreationRequest;
import com.tpkhanh.chatappapi.dto.request.LogLockAccountCreationUnLockRequest;
import com.tpkhanh.chatappapi.dto.request.UserCreationRequest;
import com.tpkhanh.chatappapi.dto.response.LogLockAccountResponse;
import com.tpkhanh.chatappapi.dto.response.UserResponse;
import com.tpkhanh.chatappapi.service.LogLockAccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/log_lock_accounts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LogLockAccountController {
    LogLockAccountService logLockAccountService;

    @PostMapping("")
    ApiResponse<LogLockAccountResponse> createLogLockAccount(@RequestBody LogLockAccountCreationRequest request) {
        return ApiResponse.<LogLockAccountResponse>builder()
                .result(logLockAccountService.createLogLockAccount(request))
                .build();
    }

    @PostMapping("/un_lock")
    ApiResponse<LogLockAccountResponse> createUnLockLogLockAccount(@RequestBody LogLockAccountCreationUnLockRequest request) {
        return ApiResponse.<LogLockAccountResponse>builder()
                .result(logLockAccountService.createUnLockLogLockAccount(request))
                .build();
    }
}
