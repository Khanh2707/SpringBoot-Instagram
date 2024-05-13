package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.PostNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostNotificationRepository extends JpaRepository<PostNotification, Integer> {
    List<PostNotification> findByUser2_IdUserOrderByDateTimeNotificationDesc(String idUser2);

    Long countByUser2_IdUserAndIsCheck(String user2, boolean isCheck);

    List<PostNotification> findAllByUser2_IdUserAndIsCheck(String user2, boolean isCheck);

    @Transactional
    void deleteByActionAndPost_IdPostAndUser1_IdUserAndUser2_IdUser(String action, Integer post, String user1, String user2);

    @Transactional
    void deleteByActionAndPost_IdPostAndUser1_IdUserAndUser2_IdUserAndCommentPost_IdCommentPost(String action, Integer post, String user1, String user2, Integer commentPost);

    @Transactional
    void deleteAllByActionAndPost_IdPost(String action, Integer post);
}
