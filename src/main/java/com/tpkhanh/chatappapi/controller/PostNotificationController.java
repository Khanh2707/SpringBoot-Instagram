package com.tpkhanh.chatappapi.controller;

import com.tpkhanh.chatappapi.dto.request.*;
import com.tpkhanh.chatappapi.dto.response.PostNotificationResponse;
import com.tpkhanh.chatappapi.dto.response.PostResponse;
import com.tpkhanh.chatappapi.dto.response.UserMessageResponse;
import com.tpkhanh.chatappapi.service.PostNotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post_notifications")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PostNotificationController {

    PostNotificationService postNotificationService;

    @GetMapping("/by_user/{idUser2}")
    ApiResponse<List<PostNotificationResponse>> getPostNotificationByUser2(@PathVariable String idUser2) {
        return ApiResponse.<List<PostNotificationResponse>>builder()
                .result(postNotificationService.getPostNotificationByUser2(idUser2))
                .build();
    }

    @GetMapping("/count_by_user/{idUser2}")
    ApiResponse<Long> countPostNotificationByUser2(@PathVariable String idUser2) {
        return ApiResponse.<Long>builder()
                .result(postNotificationService.countPostNotificationByUser2(idUser2))
                .build();
    }

    @PostMapping("")
    ApiResponse<PostNotificationResponse> createPostNotification(@RequestBody PostNotificationCreationRequest request) {
        return ApiResponse.<PostNotificationResponse>builder()
                .result(postNotificationService.createPostNotification(request))
                .build();
    }

    @PutMapping("/is_check")
    ApiResponse<List<PostNotificationResponse>> updatePostNotificationIsCheck(@RequestBody PostNotificationUpdateIsCheckRequest request) {
        return ApiResponse.<List<PostNotificationResponse>>builder()
                .result(postNotificationService.updatePostNotificationIsCheck(request))
                .build();
    }

    @DeleteMapping("/by_action_like/{action}/{idPost}/{idUser1}/{idUser2}")
    ApiResponse<String> deleteLikeNotification(@PathVariable String action, @PathVariable Integer idPost, @PathVariable String idUser1, @PathVariable String idUser2) {
        postNotificationService.deleteLikeNotification(action, idPost, idUser1, idUser2);
        return ApiResponse.<String>builder()
                .result("Like post notification has been deleted.")
                .build();
    }

    @DeleteMapping("/by_action_comment/{action}/{idPost}/{idUser1}/{idUser2}/{idCommentPost}")
    ApiResponse<String> deleteCommentNotification(@PathVariable String action, @PathVariable Integer idPost, @PathVariable String idUser1, @PathVariable String idUser2, @PathVariable Integer idCommentPost) {
        postNotificationService.deleteCommentNotification(action, idPost, idUser1, idUser2, idCommentPost);
        return ApiResponse.<String>builder()
                .result("Comment post notification has been deleted.")
                .build();
    }

    @DeleteMapping("/all_by_action/{action}/{idPost}")
    ApiResponse<String> deleteAllByAction(@PathVariable String action, @PathVariable Integer idPost) {
        postNotificationService.deleteAllByAction(action, idPost);
        return ApiResponse.<String>builder()
                .result("All Like or Comment post notification has been deleted.")
                .build();
    }
}
