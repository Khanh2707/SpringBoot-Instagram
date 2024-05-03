package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.User;
import com.tpkhanh.chatappapi.model.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMessageRepository extends JpaRepository<UserMessage, Integer> {
    @Query("SELECT um FROM UserMessage um WHERE (um.user1.idUser = :userId1 AND um.user2.idUser = :userId2) OR (um.user1.idUser = :userId2 AND um.user2.idUser = :userId1) ORDER BY um.dateTimeMessage DESC")
    List<UserMessage> findLatestMessageBetweenUsers(@Param("userId1") String userId1, @Param("userId2") String userId2);

    @Query("SELECT um " +
            "FROM UserMessage um " +
            "WHERE um.idUserMessage IN (" +
            "    SELECT MAX(um1.idUserMessage) " +
            "    FROM UserMessage um1 " +
            "    WHERE (um1.user1.idUser = :userId1 OR um1.user2.idUser = :userId1) " +
            "    GROUP BY CASE " +
            "        WHEN um1.user1.idUser = :userId1 THEN um1.user2.idUser " +
            "        ELSE um1.user1.idUser " +
            "    END" +
            ") " +
            "AND (um.user1.idUser = :userId1 OR um.user2.idUser = :userId1)")
    List<UserMessage> findRecentUserMessagesWithOtherUsers(@Param("userId1") String userId1);

    @Query("SELECT COUNT(m) FROM UserMessage m WHERE m.user2.idUser = :userId AND m.isCheck = false")
    int countUncheckedMessagesForUser(@Param("userId") String userId);
}
