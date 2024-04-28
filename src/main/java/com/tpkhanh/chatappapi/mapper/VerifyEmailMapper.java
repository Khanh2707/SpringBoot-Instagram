package com.tpkhanh.chatappapi.mapper;

import com.tpkhanh.chatappapi.dto.request.VerifyEmailGenerateRequest;
import com.tpkhanh.chatappapi.dto.response.AccountResponse;
import com.tpkhanh.chatappapi.dto.response.VerifyEmailResponse;
import com.tpkhanh.chatappapi.model.Account;
import com.tpkhanh.chatappapi.model.VerifyEmail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VerifyEmailMapper {
    VerifyEmail toVerifyEmail(VerifyEmailGenerateRequest request);
    VerifyEmailResponse toVerifyEmailResponse(VerifyEmail verifyEmail);
}
