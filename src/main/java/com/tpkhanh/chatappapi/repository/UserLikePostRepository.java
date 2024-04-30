package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.UserLikePost;
import com.tpkhanh.chatappapi.model.UserLikePostKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLikePostRepository extends JpaRepository<UserLikePost, UserLikePostKey> {
    boolean existsByUser_IdUserAndPost_IdPost(String userId, Integer postId);
    long countByPost_IdPost(Integer postId);
    List<UserLikePost> findAllByPost_IdPost(Integer postId);
}
