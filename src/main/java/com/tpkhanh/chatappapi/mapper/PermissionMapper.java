package com.tpkhanh.chatappapi.mapper;

import com.tpkhanh.chatappapi.dto.request.AccountCreationRequest;
import com.tpkhanh.chatappapi.dto.request.AccountUpdateRequest;
import com.tpkhanh.chatappapi.dto.request.PermissionRequest;
import com.tpkhanh.chatappapi.dto.response.AccountResponse;
import com.tpkhanh.chatappapi.dto.response.PermissionResponse;
import com.tpkhanh.chatappapi.model.Account;
import com.tpkhanh.chatappapi.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
