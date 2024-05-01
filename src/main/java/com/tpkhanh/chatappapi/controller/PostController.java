package com.tpkhanh.chatappapi.controller;

import com.tpkhanh.chatappapi.dto.request.ApiResponse;
import com.tpkhanh.chatappapi.dto.request.PostCreationRequest;
import com.tpkhanh.chatappapi.dto.response.PostResponse;
import com.tpkhanh.chatappapi.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PostController {

    PostService postService;

    @GetMapping("/count_post/{idUser}")
    ApiResponse<Long> countAllByUser(@PathVariable String idUser) {
        return ApiResponse.<Long>builder()
                .result(postService.countAllByUser(idUser))
                .build();
    }

    @GetMapping("")
    ApiResponse<List<PostResponse>> getAllPosts() {
        return ApiResponse.<List<PostResponse>>builder()
                .result(postService.getAllPosts())
                .build();
    }

    @GetMapping("/by_user/{idUser}")
    ApiResponse<List<PostResponse>> getAllPostsByIdUser(@PathVariable String idUser) {
        return ApiResponse.<List<PostResponse>>builder()
                .result(postService.getAllPostsByIdUser(idUser))
                .build();
    }

    @GetMapping("/{idPost}")
    ApiResponse<PostResponse> getPostById(@PathVariable Integer idPost) {
        return ApiResponse.<PostResponse>builder()
                .result(postService.getPostById(idPost))
                .build();
    }

    @PostMapping("")
    ApiResponse<PostResponse> createPost(@RequestBody PostCreationRequest request) {
        return ApiResponse.<PostResponse>builder()
                .result(postService.createPost(request))
                .build();
    }

    @DeleteMapping("/{idPost}")
    ApiResponse<String> deletePost(@PathVariable Integer idPost) {
        postService.deletePost(idPost);
        return ApiResponse.<String>builder()
                .result("Post has been deleted")
                .build();
    }
}
