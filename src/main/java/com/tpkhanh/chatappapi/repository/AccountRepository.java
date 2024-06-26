package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByAccount(String account);
    Optional<Account> findByAccount(String account);
    List<Account> findByUser_IdUserContaining(String idUser);
}
