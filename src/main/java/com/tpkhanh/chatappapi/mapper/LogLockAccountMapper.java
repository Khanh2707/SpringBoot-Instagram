package com.tpkhanh.chatappapi.mapper;

import com.tpkhanh.chatappapi.dto.request.LogLockAccountCreationRequest;
import com.tpkhanh.chatappapi.dto.request.LogLockAccountCreationUnLockRequest;
import com.tpkhanh.chatappapi.dto.response.LogLockAccountResponse;
import com.tpkhanh.chatappapi.model.LogLockAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LogLockAccountMapper {
    @Mapping(target = "account", ignore = true)
    LogLockAccount toLogLockAccount(LogLockAccountCreationRequest request);

    @Mapping(target = "account", ignore = true)
    LogLockAccount toLogLockAccount(LogLockAccountCreationUnLockRequest request);

    LogLockAccountResponse toLogLockAccountResponse(LogLockAccount logLockAccount);
}
