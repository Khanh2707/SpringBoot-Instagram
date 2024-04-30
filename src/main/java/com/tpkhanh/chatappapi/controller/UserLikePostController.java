package com.tpkhanh.chatappapi.controller;

import com.tpkhanh.chatappapi.dto.request.ApiResponse;
import com.tpkhanh.chatappapi.dto.request.UserLikePostCreationRequest;
import com.tpkhanh.chatappapi.dto.response.UserLikePostResponse;
import com.tpkhanh.chatappapi.model.UserLikePost;
import com.tpkhanh.chatappapi.service.UserLikePostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user_like_post")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserLikePostController {

    UserLikePostService userLikePostService;

    @GetMapping("/count_user/{idPost}")
    ApiResponse<Long> countUserLikePost(@PathVariable Integer idPost) {
        return ApiResponse.<Long>builder()
                .result(userLikePostService.countUserLikePost(idPost))
                .build();
    }

    @GetMapping("/check_like/{idUser}/{idPost}")
    ApiResponse<Boolean> checkUserLikePost(@PathVariable String idUser, @PathVariable Integer idPost) {
        return ApiResponse.<Boolean>builder()
                .result(userLikePostService.checkUserLikePost(idUser, idPost))
                .build();
    }

    @GetMapping("/by_post/{idPost}")
    ApiResponse<List<UserLikePostResponse>> findAllUserByPost(@PathVariable Integer idPost) {
        return ApiResponse.<List<UserLikePostResponse>>builder()
                .result(userLikePostService.findAllUserByPost(idPost))
                .build();
    }

    @PostMapping("")
    ApiResponse<UserLikePostResponse> createUserLikePost(@RequestBody UserLikePostCreationRequest request) {
        return ApiResponse.<UserLikePostResponse>builder()
                .result(userLikePostService.createUserLikePost(request))
                .build();
    }

    @DeleteMapping("/{idUser}/{idPost}")
    ApiResponse<String> deleteUserLikePost(@PathVariable String idUser, @PathVariable Integer idPost) {
        userLikePostService.deleteUserLikePost(idUser, idPost);
        return ApiResponse.<String>builder()
                .result("Like of user has been deleted")
                .build();
    }
}
