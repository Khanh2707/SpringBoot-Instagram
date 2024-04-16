package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByAccount(String account);
}
