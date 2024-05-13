package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.request.AccountUpdateRoleRequest;
import com.tpkhanh.chatappapi.dto.request.PostNotificationCreationRequest;
import com.tpkhanh.chatappapi.dto.request.PostNotificationUpdateIsCheckRequest;
import com.tpkhanh.chatappapi.dto.request.UserMessageCreationRequest;
import com.tpkhanh.chatappapi.dto.response.AccountResponse;
import com.tpkhanh.chatappapi.dto.response.PostNotificationResponse;
import com.tpkhanh.chatappapi.dto.response.UserMessageResponse;
import com.tpkhanh.chatappapi.exception.AppException;
import com.tpkhanh.chatappapi.exception.ErrorCode;
import com.tpkhanh.chatappapi.mapper.PostNotificationMapper;
import com.tpkhanh.chatappapi.model.*;
import com.tpkhanh.chatappapi.repository.CommentPostRepository;
import com.tpkhanh.chatappapi.repository.PostNotificationRepository;
import com.tpkhanh.chatappapi.repository.PostRepository;
import com.tpkhanh.chatappapi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PostNotificationService {

    PostNotificationMapper postNotificationMapper;

    PostRepository postRepository;

    UserRepository userRepository;

    CommentPostRepository commentPostRepository;

    PostNotificationRepository postNotificationRepository;

    public List<PostNotificationResponse> getPostNotificationByUser2(String idUser2) {
        return postNotificationRepository.findByUser2_IdUserOrderByDateTimeNotificationDesc(idUser2).stream()
                .map(postNotificationMapper::toPostNotificationResponse)
                .toList();
    }

    public Long countPostNotificationByUser2(String idUser2) {
        return postNotificationRepository.countByUser2_IdUserAndIsCheck(idUser2, false);
    }

    public PostNotificationResponse createPostNotification(PostNotificationCreationRequest request) {

        PostNotification postNotification = postNotificationMapper.toPostNotification(request);

        postNotification.setIsCheck(false);

        postNotification.setDateTimeNotification(LocalDateTime.now().withNano(0));

        Post post = postRepository.findById(request.getPost()).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        postNotification.setPost(post);

        User user1 = userRepository.findById(request.getUser1()).orElseThrow(() -> new AppException(ErrorCode.ID_USER_NOT_EXISTED));
        postNotification.setUser1(user1);

        User user2 = userRepository.findById(request.getUser2()).orElseThrow(() -> new AppException(ErrorCode.ID_USER_NOT_EXISTED));
        postNotification.setUser2(user2);

        if (request.getAction().equals("COMMENT")) {
            CommentPost commentPost = commentPostRepository.findById(request.getCommentPost()).orElseThrow(() -> new AppException(ErrorCode.COMMENT_POST_NOT_FOUND));
            postNotification.setCommentPost(commentPost);
        }

        return postNotificationMapper.toPostNotificationResponse(postNotificationRepository.save(postNotification));
    }

    public List<PostNotificationResponse> updatePostNotificationIsCheck(PostNotificationUpdateIsCheckRequest request) {
        List<PostNotification> postNotifications = postNotificationRepository.findAllByUser2_IdUserAndIsCheck(request.getUser2(), false);

        for (PostNotification postNotification : postNotifications) {
            postNotification.setIsCheck(true);
        }

        List<PostNotification> updatePostNotifications = postNotificationRepository.saveAll(postNotifications);

        return updatePostNotifications.stream()
                .map(postNotificationMapper::toPostNotificationResponse)
                .toList();
    }

    public void deleteLikeNotification(String action, Integer post, String user1, String user2) {
        postNotificationRepository.deleteByActionAndPost_IdPostAndUser1_IdUserAndUser2_IdUser(action, post, user1, user2);
    }

    public void deleteCommentNotification(String action, Integer post, String user1, String user2, Integer commentPost) {
        postNotificationRepository.deleteByActionAndPost_IdPostAndUser1_IdUserAndUser2_IdUserAndCommentPost_IdCommentPost(action, post, user1, user2, commentPost);
    }

    public void deleteAllByAction(String action, Integer post) {
        postNotificationRepository.deleteAllByActionAndPost_IdPost(action, post);
    }
}
