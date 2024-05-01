package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.request.UserCommentPostCreationRequest;
import com.tpkhanh.chatappapi.dto.request.UserLikePostCreationRequest;
import com.tpkhanh.chatappapi.dto.response.UserCommentPostResponse;
import com.tpkhanh.chatappapi.dto.response.UserLikePostResponse;
import com.tpkhanh.chatappapi.exception.AppException;
import com.tpkhanh.chatappapi.exception.ErrorCode;
import com.tpkhanh.chatappapi.mapper.UserCommentPostMapper;
import com.tpkhanh.chatappapi.model.*;
import com.tpkhanh.chatappapi.repository.CommentPostRepository;
import com.tpkhanh.chatappapi.repository.PostRepository;
import com.tpkhanh.chatappapi.repository.UserCommentPostRepository;
import com.tpkhanh.chatappapi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserCommentPostService {

    UserCommentPostMapper userCommentPostMapper;

    UserRepository userRepository;

    CommentPostRepository commentPostRepository;

    PostRepository postRepository;

    UserCommentPostRepository userCommentPostRepository;

    public long countUserCommentPost(Integer idPost) {
        return userCommentPostRepository.countByPost_IdPost(idPost);
    }

    public List<UserCommentPostResponse> findAllByPost(Integer idPost) {
        return userCommentPostRepository.findAllByPost_IdPostOrderByCommentPost_DateTimeCommentDesc(idPost).stream()
                .map(userCommentPostMapper::toUserCommentPostResponse)
                .toList();
    }

    public UserCommentPostResponse createUserCommentPost(UserCommentPostCreationRequest request) {

        UserCommentPost userCommentPost = userCommentPostMapper.toUserCommentPost(request);

        User user = userRepository.findById(request.getId_user_user_comment_post()).orElseThrow(() -> new AppException(ErrorCode.ID_USER_NOT_EXISTED));
        userCommentPost.setUser(user);

        CommentPost commentPost = commentPostRepository.findById(request.getId_comment_post_user_comment_post()).orElseThrow(() -> new AppException(ErrorCode.COMMENT_POST_NOT_FOUND));
        userCommentPost.setCommentPost(commentPost);

        Post post = postRepository.findById(request.getId_post_user_comment_post()).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        userCommentPost.setPost(post);

        UserCommentPostKey userCommentPostKey = new UserCommentPostKey();
        userCommentPostKey.setIdUser(user.getIdUser());
        userCommentPostKey.setIdCommentPost(commentPost.getIdCommentPost());
        userCommentPostKey.setIdPost(post.getIdPost());
        userCommentPost.setId(userCommentPostKey);

        return userCommentPostMapper.toUserCommentPostResponse(userCommentPostRepository.save(userCommentPost));
    }

    public void deleteUserCommentPost(String idUser, Integer idCommentPost, Integer idPost) {
        UserCommentPostKey userCommentPostKey = new UserCommentPostKey(idUser, idCommentPost, idPost);
        userCommentPostRepository.deleteById(userCommentPostKey);
    }
}
