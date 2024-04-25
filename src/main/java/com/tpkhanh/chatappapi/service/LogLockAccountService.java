package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.request.LogLockAccountCreationRequest;
import com.tpkhanh.chatappapi.dto.request.LogLockAccountCreationUnLockRequest;
import com.tpkhanh.chatappapi.dto.response.LogLockAccountResponse;
import com.tpkhanh.chatappapi.exception.AppException;
import com.tpkhanh.chatappapi.exception.ErrorCode;
import com.tpkhanh.chatappapi.mapper.LogLockAccountMapper;
import com.tpkhanh.chatappapi.model.Account;
import com.tpkhanh.chatappapi.model.LogLockAccount;
import com.tpkhanh.chatappapi.repository.AccountRepository;
import com.tpkhanh.chatappapi.repository.LogLockAccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LogLockAccountService {

    LogLockAccountRepository logLockAccountRepository;

    LogLockAccountMapper logLockAccountMapper;

    AccountRepository accountRepository;

    public LogLockAccountResponse createLogLockAccount(LogLockAccountCreationRequest request) {

        boolean checkExisted = logLockAccountRepository.existsByAccount_IdAccount(request.getIdAccountLogLockAccount());

        if (checkExisted) {
            if (logLockAccountRepository.findFirstByAccount_IdAccountOrderByDateTimeLockDesc(request.getIdAccountLogLockAccount()).getStateLock())
                throw new AppException(ErrorCode.ACCOUNT_HAS_BEEN_LOCK);
        }

        LogLockAccount logLockAccount = logLockAccountMapper.toLogLockAccount(request);

        logLockAccount.setStateLock(true);
        logLockAccount.setDateTimeLock(LocalDateTime.now());

        Account account = accountRepository.findById(request.getIdAccountLogLockAccount()).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        logLockAccount.setAccount(account);

        return logLockAccountMapper.toLogLockAccountResponse(logLockAccountRepository.save(logLockAccount));
    }

    public LogLockAccountResponse createUnLockLogLockAccount(LogLockAccountCreationUnLockRequest request) {

        boolean checkExisted = logLockAccountRepository.existsByAccount_IdAccount(request.getIdAccountLogLockAccount());

        if (checkExisted) {
            if (logLockAccountRepository.findFirstByAccount_IdAccountOrderByDateTimeLockDesc(request.getIdAccountLogLockAccount()).getStateLock()) {
                LogLockAccount logLockAccount = logLockAccountMapper.toLogLockAccount(request);

                logLockAccount.setReasonLock("");
                logLockAccount.setStateLock(false);
                logLockAccount.setDateTimeLock(LocalDateTime.now());

                Account account = accountRepository.findById(request.getIdAccountLogLockAccount()).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
                logLockAccount.setAccount(account);

                return logLockAccountMapper.toLogLockAccountResponse(logLockAccountRepository.save(logLockAccount));
            }
        }
        throw new AppException(ErrorCode.ACCOUNT_NOT_LOCK);
    }
}
