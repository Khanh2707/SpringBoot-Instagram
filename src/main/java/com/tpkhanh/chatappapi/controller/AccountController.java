package com.tpkhanh.chatappapi.controller;

import com.tpkhanh.chatappapi.dto.request.AccountCreationRequest;
import com.tpkhanh.chatappapi.dto.request.AccountUpdateRequest;
import com.tpkhanh.chatappapi.dto.request.ApiResponse;
import com.tpkhanh.chatappapi.dto.response.AccountResponse;
import com.tpkhanh.chatappapi.model.Account;
import com.tpkhanh.chatappapi.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {

    AccountService accountService;

    @GetMapping("")
    List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{accountId}")
    AccountResponse getAccount(@PathVariable Integer accountId) {
        return accountService.getAccountById(accountId);
    }

    @PostMapping("")
    ApiResponse<Account> createAccount(@RequestBody AccountCreationRequest request) {
        ApiResponse<Account> apiResponse = new ApiResponse<>();

        apiResponse.setResult(accountService.createAccount(request));

        return apiResponse;
    }

    @PutMapping("/{accountId}")
    AccountResponse updateAccount(@PathVariable Integer accountId, @RequestBody AccountUpdateRequest request) {
        return accountService.updateAccount(accountId, request);
    }

    @DeleteMapping("/{accountId}")
    void deleteAccount(@PathVariable Integer accountId) {
        accountService.deleteAccount(accountId);
    }
}
