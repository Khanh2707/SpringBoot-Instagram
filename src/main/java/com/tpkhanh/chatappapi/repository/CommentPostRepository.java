package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.CommentPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentPostRepository extends JpaRepository<CommentPost, Integer> {
}
