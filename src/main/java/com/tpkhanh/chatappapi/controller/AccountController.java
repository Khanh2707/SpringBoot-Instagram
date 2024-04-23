package com.tpkhanh.chatappapi.controller;

import com.tpkhanh.chatappapi.dto.request.AccountCreationRequest;
import com.tpkhanh.chatappapi.dto.request.AccountUpdatePasswordRequest;
import com.tpkhanh.chatappapi.dto.request.AccountUpdateRequest;
import com.tpkhanh.chatappapi.dto.request.ApiResponse;
import com.tpkhanh.chatappapi.dto.response.AccountResponse;
import com.tpkhanh.chatappapi.model.Account;
import com.tpkhanh.chatappapi.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountController {

    AccountService accountService;

    @GetMapping("")
    ApiResponse<List<AccountResponse>> getAllAccounts() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Account: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<AccountResponse>>builder()
                .result(accountService.getAllAccounts())
                .build();
    }

    @GetMapping("/{accountId}")
    ApiResponse<AccountResponse> getAccount(@PathVariable Integer accountId) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.getAccountById(accountId))
                .build();
    }

    @GetMapping("/myAccount")
    ApiResponse<AccountResponse> getMyAccount() {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.getMyAccount())
                .build();
    }

    @PostMapping("")
    ApiResponse<AccountResponse> createAccount(@RequestBody AccountCreationRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.createAccount(request))
                .build();
    }

    @PutMapping("/{accountId}")
    ApiResponse<AccountResponse> updateAccount(@PathVariable Integer accountId, @RequestBody AccountUpdateRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.updateAccount(accountId, request))
                .build();
    }

    @PutMapping("password/{accountId}")
    ApiResponse<AccountResponse> updateAccountPassword(@PathVariable Integer accountId, @RequestBody AccountUpdatePasswordRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.updateAccountPassword(accountId, request))
                .build();
    }

    @DeleteMapping("/{accountId}")
    ApiResponse<String> deleteAccount(@PathVariable Integer accountId) {
        accountService.deleteAccount(accountId);
        return ApiResponse.<String>builder()
                .result("Account has been deleted")
                .build();
    }
}
