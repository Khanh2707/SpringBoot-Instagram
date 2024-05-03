package com.tpkhanh.chatappapi.controller;

import com.tpkhanh.chatappapi.dto.request.ApiResponse;
import com.tpkhanh.chatappapi.dto.request.UserMessageCreationRequest;
import com.tpkhanh.chatappapi.dto.request.UserMessageUpdateIsCheckRequest;
import com.tpkhanh.chatappapi.dto.response.UserMessageResponse;
import com.tpkhanh.chatappapi.service.UserMessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user_message")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserMessageController {

    UserMessageService userMessageService;

    @GetMapping("/count_message_not_check_by_user_1/{idUser1}")
    ApiResponse<Integer> countUncheckedMessagesByUser1Id(@PathVariable String idUser1) {
        return ApiResponse.<Integer>builder()
                .result(userMessageService.countUncheckedMessagesForUser(idUser1))
                .build();
    }

    @GetMapping("/all_latest_message_by_user_1/{idUser1}")
    ApiResponse<List<UserMessageResponse>> findRecentUserMessagesWithOtherUsers(@PathVariable String idUser1) {
        return ApiResponse.<List<UserMessageResponse>>builder()
                .result(userMessageService.findRecentUserMessagesWithOtherUsers(idUser1))
                .build();
    }

    @GetMapping("/{idUser1}/{idUser2}")
    ApiResponse<List<UserMessageResponse>> getMessagesWithOtherUser(@PathVariable String idUser1, @PathVariable String idUser2) {
        return ApiResponse.<List<UserMessageResponse>>builder()
                .result(userMessageService.getMessagesWithOtherUser(idUser1, idUser2))
                .build();
    }

    @PostMapping("")
    ApiResponse<UserMessageResponse> createUserMessage(@RequestBody UserMessageCreationRequest request) {
        return ApiResponse.<UserMessageResponse>builder()
                .result(userMessageService.createUserMessage(request))
                .build();
    }

    @PutMapping("/is_check")
    ApiResponse<List<UserMessageResponse>> updateUserMessageIsCheck(@RequestBody UserMessageUpdateIsCheckRequest request) {
        return ApiResponse.<List<UserMessageResponse>>builder()
                .result(userMessageService.updateUserMessageIsCheck(request))
                .build();
    }

    @DeleteMapping("/{idUserMessage}")
    ApiResponse<String> deleteById(@PathVariable Integer idUserMessage) {
        userMessageService.deleteById(idUserMessage);
        return ApiResponse.<String>builder()
                .result("User message has been deleted")
                .build();
    }
}
