package com.tpkhanh.chatappapi.controller;

import com.tpkhanh.chatappapi.dto.request.ApiResponse;
import com.tpkhanh.chatappapi.dto.request.CommentPostCreationRequest;
import com.tpkhanh.chatappapi.dto.request.UserCommentPostCreationRequest;
import com.tpkhanh.chatappapi.dto.response.CommentPostResponse;
import com.tpkhanh.chatappapi.dto.response.UserCommentPostResponse;
import com.tpkhanh.chatappapi.service.CommentPostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment_post")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CommentPostController {

    CommentPostService commentPostService;

    @PostMapping("")
    ApiResponse<CommentPostResponse> createCommentPost(@RequestBody CommentPostCreationRequest request) {
        return ApiResponse.<CommentPostResponse>builder()
                .result(commentPostService.createCommentPost(request))
                .build();
    }

    @DeleteMapping("/{idCommentPost}")
    ApiResponse<String> deleteCommentPost(@PathVariable Integer idCommentPost) {
        commentPostService.deleteCommentPost(idCommentPost);
        return ApiResponse.<String>builder()
                .result("Comment post has been deleted")
                .build();
    }
}
