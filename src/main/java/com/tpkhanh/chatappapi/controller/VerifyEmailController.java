package com.tpkhanh.chatappapi.controller;

import com.tpkhanh.chatappapi.dto.request.ApiResponse;
import com.tpkhanh.chatappapi.dto.request.VerifyEmailGenerateRequest;
import com.tpkhanh.chatappapi.dto.response.VerifyEmailResponse;
import com.tpkhanh.chatappapi.service.VerifyEmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verify_email")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class VerifyEmailController {

    VerifyEmailService verifyEmailService;

    @PostMapping("")
    ApiResponse<VerifyEmailResponse> generateVerifyEmail(@RequestBody VerifyEmailGenerateRequest request) {
        return ApiResponse.<VerifyEmailResponse>builder()
                .result(verifyEmailService.verifyEmailGenerate(request))
                .build();
    }
}
