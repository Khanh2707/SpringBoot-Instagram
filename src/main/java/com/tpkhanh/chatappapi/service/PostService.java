package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.request.PostCreationRequest;
import com.tpkhanh.chatappapi.dto.response.PostResponse;
import com.tpkhanh.chatappapi.exception.AppException;
import com.tpkhanh.chatappapi.exception.ErrorCode;
import com.tpkhanh.chatappapi.mapper.PostMapper;
import com.tpkhanh.chatappapi.model.Post;
import com.tpkhanh.chatappapi.model.User;
import com.tpkhanh.chatappapi.repository.PostRepository;
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
public class PostService {

    PostRepository postRepository;

    PostMapper postMapper;

    UserRepository userRepository;

    public List<PostResponse> getRandomPostsExcludingUser(String idUser) {
        return postRepository.getRandomPostsExcludingUser(idUser).stream()
                .map(postMapper::toPostResponse)
                .toList();
    }

    public long countAllByUser(String idUser) {
        return postRepository.countAllByUser_IdUser(idUser);
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toPostResponse)
                .toList();
    }

    public List<PostResponse> getAllPostsByIdUser(String idUser) {
        return postRepository.findAllByUser_IdUser(idUser).stream()
                .map(postMapper::toPostResponse)
                .toList();
    }

    public PostResponse getPostById(Integer postId) {
        return postMapper.toPostResponse(postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND)));
    }

    public PostResponse createPost(PostCreationRequest request) {

        Post post = postMapper.toPost(request);

        post.setDateTimeCreate(LocalDateTime.now().withNano(0));

        User user = userRepository.findById(request.getUser()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        post.setUser(user);

        return postMapper.toPostResponse(postRepository.save(post));
    }

    public void deletePost(Integer idPost) {
        postRepository.deleteById(idPost);
    }
}
