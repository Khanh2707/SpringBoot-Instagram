package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByIdUser(String idUser);
    @Query("SELECT u FROM User u JOIN u.posts p WHERE p.idPost = :idPost")
    Optional<User> findByPostId(Integer idPost);
}
