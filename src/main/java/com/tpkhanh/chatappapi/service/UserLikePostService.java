package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.request.SearchHistoryCreationRequest;
import com.tpkhanh.chatappapi.dto.request.UserLikePostCreationRequest;
import com.tpkhanh.chatappapi.dto.response.SearchHistoryResponse;
import com.tpkhanh.chatappapi.dto.response.UserLikePostResponse;
import com.tpkhanh.chatappapi.exception.AppException;
import com.tpkhanh.chatappapi.exception.ErrorCode;
import com.tpkhanh.chatappapi.mapper.UserLikePostMapper;
import com.tpkhanh.chatappapi.model.*;
import com.tpkhanh.chatappapi.repository.PostRepository;
import com.tpkhanh.chatappapi.repository.UserLikePostRepository;
import com.tpkhanh.chatappapi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserLikePostService {

    UserLikePostMapper userLikePostMapper;

    UserRepository userRepository;

    PostRepository postRepository;

    UserLikePostRepository userLikePostRepository;

    public long countUserLikePost(Integer idPost) {
        return userLikePostRepository.countByPost_IdPost(idPost);
    }

    public boolean checkUserLikePost(String idUser, Integer idPost) {
        return userLikePostRepository.existsByUser_IdUserAndPost_IdPost(idUser, idPost);
    }

    public List<UserLikePostResponse> findAllUserByPost(Integer idPost) {
        return userLikePostRepository.findAllByPost_IdPost(idPost).stream()
                .map(userLikePostMapper::toUserLikePostResponse)
                .toList();
    }

    public UserLikePostResponse createUserLikePost(UserLikePostCreationRequest request) {

        UserLikePost userLikePost = userLikePostMapper.toUserLikePost(request);

        User user = userRepository.findById(request.getId_user_user_like_post()).orElseThrow(() -> new AppException(ErrorCode.ID_USER_NOT_EXISTED));
        userLikePost.setUser(user);

        Post post = postRepository.findById(request.getId_post_user_like_post()).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        userLikePost.setPost(post);

        UserLikePostKey userLikePostKey = new UserLikePostKey();
        userLikePostKey.setIdUser(user.getIdUser());
        userLikePostKey.setIdPost(post.getIdPost());
        userLikePost.setId(userLikePostKey);

        userLikePost.setDateTimeLike(LocalDateTime.now());

        return userLikePostMapper.toUserLikePostResponse(userLikePostRepository.save(userLikePost));
    }

    public void deleteUserLikePost(String idUser, Integer idPost) {
        UserLikePostKey userLikePostKey = new UserLikePostKey(idUser, idPost);
        userLikePostRepository.deleteById(userLikePostKey);
    }

    public void deleteAllUserLikePostByPost(Integer idPost) {
        List<UserLikePost> userLikePosts = userLikePostRepository.findByPost_IdPost(idPost);
        userLikePostRepository.deleteAll(userLikePosts);
    }
}
