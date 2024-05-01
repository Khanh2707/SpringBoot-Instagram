package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.UserCommentPost;
import com.tpkhanh.chatappapi.model.UserCommentPostKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCommentPostRepository extends JpaRepository<UserCommentPost, UserCommentPostKey> {
    long countByPost_IdPost(Integer postId);
    List<UserCommentPost> findAllByPost_IdPostOrderByCommentPost_DateTimeCommentDesc(Integer idPost);
    List<UserCommentPost> findByPost_IdPost(Integer idPost);
}
