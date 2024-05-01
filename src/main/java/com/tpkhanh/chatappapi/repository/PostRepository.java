package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Collection<Post> findAllByUser_IdUser(String idUser);
    long countAllByUser_IdUser(String idUser);
}
