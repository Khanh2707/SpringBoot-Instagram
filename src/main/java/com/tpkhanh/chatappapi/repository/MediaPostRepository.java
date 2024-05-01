package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.MediaPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaPostRepository extends JpaRepository<MediaPost, Integer> {
    List<MediaPost> findByPost_IdPost(Integer idPost);
}
