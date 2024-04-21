package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByIdUser(String idUser);
}
