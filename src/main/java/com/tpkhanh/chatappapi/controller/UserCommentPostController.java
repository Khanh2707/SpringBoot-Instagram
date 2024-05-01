package com.tpkhanh.chatappapi.controller;

import com.tpkhanh.chatappapi.dto.request.ApiResponse;
import com.tpkhanh.chatappapi.dto.request.UserCommentPostCreationRequest;
import com.tpkhanh.chatappapi.dto.request.UserLikePostCreationRequest;
import com.tpkhanh.chatappapi.dto.response.UserCommentPostResponse;
import com.tpkhanh.chatappapi.dto.response.UserLikePostResponse;
import com.tpkhanh.chatappapi.service.UserCommentPostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user_comment_post")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserCommentPostController {

    UserCommentPostService userCommentPostService;

    @GetMapping("/count_user/{idPost}")
    ApiResponse<Long> countUserCommentPost(@PathVariable Integer idPost) {
        return ApiResponse.<Long>builder()
                .result(userCommentPostService.countUserCommentPost(idPost))
                .build();
    }

    @GetMapping("/by_post/{idPost}")
    ApiResponse<List<UserCommentPostResponse>> findAllByPost(@PathVariable Integer idPost) {
        return ApiResponse.<List<UserCommentPostResponse>>builder()
                .result(userCommentPostService.findAllByPost(idPost))
                .build();
    }

    @PostMapping("")
    ApiResponse<UserCommentPostResponse> createUserCommentPost(@RequestBody UserCommentPostCreationRequest request) {
        return ApiResponse.<UserCommentPostResponse>builder()
                .result(userCommentPostService.createUserCommentPost(request))
                .build();
    }

    @DeleteMapping("/{idUser}/{idCommentPost}/{idPost}")
    ApiResponse<String> deleteUserCommentPost(@PathVariable String idUser, @PathVariable Integer idCommentPost, @PathVariable Integer idPost) {
        userCommentPostService.deleteUserCommentPost(idUser, idCommentPost, idPost);
        return ApiResponse.<String>builder()
                .result("Comment of user has been deleted")
                .build();
    }

    @DeleteMapping("/all_by_post/{idPost}")
    ApiResponse<String> deleteCommentsAndUserCommentsByPostId(@PathVariable Integer idPost) {
        userCommentPostService.deleteCommentsAndUserCommentsByPostId(idPost);
        return ApiResponse.<String>builder()
                .result("Post has been deleted")
                .build();
    }
}
