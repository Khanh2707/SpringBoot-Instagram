package com.tpkhanh.chatappapi.controller;

import com.tpkhanh.chatappapi.dto.request.*;
import com.tpkhanh.chatappapi.dto.response.UserResponse;
import com.tpkhanh.chatappapi.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {

    UserService userService;

    @GetMapping("/{keyword}/{idUser}")
    ApiResponse<List<UserResponse>> getUsersByKeyword(@PathVariable String keyword, @PathVariable String idUser) {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsersByKeyword(keyword, idUser))
                .build();
    }

    @PostMapping("")
    ApiResponse<UserResponse> createUser(@RequestBody UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateInfoUser(@PathVariable String userId, @RequestBody UserUpdateInfoRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUserInfo(userId, request))
                .build();
    }

    @PutMapping("/avatar/{userId}")
    ApiResponse<UserResponse> updateAvatarUser(@PathVariable String userId, @RequestParam("avatar") MultipartFile avatar) throws IOException {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUserAvatar(userId, avatar))
                .build();
    }

    @DeleteMapping("/avatar/{userId}")
    ApiResponse<String> deleteUserAvatar(@PathVariable String userId) throws IOException {
        userService.deleteUserAvatar(userId);
        return ApiResponse.<String>builder()
                .result("Avatar has been deleted")
                .build();
    }
}
