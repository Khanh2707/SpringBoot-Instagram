package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.LogLockAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogLockAccountRepository extends JpaRepository<LogLockAccount, Integer> {
    boolean existsByAccount_IdAccount(Integer accountId);
    LogLockAccount findFirstByAccount_IdAccountOrderByDateTimeLockDesc(Integer accountId);
}
