package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.request.AccountCreationRequest;
import com.tpkhanh.chatappapi.dto.request.AccountUpdateRequest;
import com.tpkhanh.chatappapi.exception.AppException;
import com.tpkhanh.chatappapi.exception.ErrorCode;
import com.tpkhanh.chatappapi.model.Account;
import com.tpkhanh.chatappapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Integer id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account createAccount(AccountCreationRequest request) {
        Account account = new Account();

        if (accountRepository.existsByAccount(request.getAccount()))
            throw new AppException(ErrorCode.ACCOUNT_EXISTED);

        account.setAccount(request.getAccount());
        account.setPassword(request.getPassword());
        account.setDate_time_create(request.getDate_time_create());
        account.setState_active(request.getState_active());
        account.setLast_time_active(request.getLast_time_active());

        return accountRepository.save(account);
    }

    public Account updateAccount(Integer accountId, AccountUpdateRequest request) {
        Account account = getAccountById(accountId);

        account.setPassword(request.getPassword());
        account.setDate_time_create(request.getDate_time_create());
        account.setState_active(request.getState_active());
        account.setLast_time_active(request.getLast_time_active());

        return accountRepository.save(account);
    }

    public void deleteAccount(Integer accountId) {
        accountRepository.deleteById(accountId);
    }
}
