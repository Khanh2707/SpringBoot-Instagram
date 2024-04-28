package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.VerifyEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyEmailRepository extends JpaRepository<VerifyEmail, String> {
    VerifyEmail findTop1ByEmailOrderByDateTimeExpiryDesc(String email);
}
