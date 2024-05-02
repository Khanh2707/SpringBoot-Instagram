package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Collection<Post> findAllByUser_IdUser(String idUser);
    long countAllByUser_IdUser(String idUser);
    @Query("SELECT p FROM Post p WHERE p.user.idUser != :userId ORDER BY RAND()")
    List<Post> getRandomPostsExcludingUser(@Param("userId") String idUser);
}
