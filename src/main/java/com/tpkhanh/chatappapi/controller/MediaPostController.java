package com.tpkhanh.chatappapi.controller;

import com.tpkhanh.chatappapi.dto.request.ApiResponse;
import com.tpkhanh.chatappapi.dto.response.MediaPostResponse;
import com.tpkhanh.chatappapi.service.MediaPostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/media_posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class MediaPostController {

    MediaPostService mediaPostService;

    @PostMapping("")
    ApiResponse<MediaPostResponse> createMediaPost(@RequestPart MultipartFile fileMedia, @RequestParam String type, @RequestParam Integer post) throws IOException {
        return ApiResponse.<MediaPostResponse>builder()
                .result(mediaPostService.createMediaPost(fileMedia, type, post))
                .build();
    }
}
