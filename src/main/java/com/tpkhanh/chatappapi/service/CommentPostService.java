package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.request.CommentPostCreationRequest;
import com.tpkhanh.chatappapi.dto.request.PostCreationRequest;
import com.tpkhanh.chatappapi.dto.response.CommentPostResponse;
import com.tpkhanh.chatappapi.dto.response.PostResponse;
import com.tpkhanh.chatappapi.exception.AppException;
import com.tpkhanh.chatappapi.exception.ErrorCode;
import com.tpkhanh.chatappapi.mapper.CommentPostMapper;
import com.tpkhanh.chatappapi.model.CommentPost;
import com.tpkhanh.chatappapi.model.Post;
import com.tpkhanh.chatappapi.model.User;
import com.tpkhanh.chatappapi.model.UserCommentPostKey;
import com.tpkhanh.chatappapi.repository.CommentPostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CommentPostService {

    CommentPostMapper commentPostMapper;

    CommentPostRepository commentPostRepository;

    public CommentPostResponse createCommentPost(CommentPostCreationRequest request) {

        CommentPost commentPost = commentPostMapper.toCommentPost(request);

        commentPost.setDateTimeComment(LocalDateTime.now().withNano(0));

        return commentPostMapper.toCommentPostResponse(commentPostRepository.save(commentPost));
    }

    public void deleteCommentPost(Integer idCommentPost) {
        commentPostRepository.deleteById(idCommentPost);
    }
}
