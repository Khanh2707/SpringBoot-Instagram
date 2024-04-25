package com.tpkhanh.chatappapi.mapper;

import com.tpkhanh.chatappapi.dto.request.PermissionRequest;
import com.tpkhanh.chatappapi.dto.response.PermissionResponse;
import com.tpkhanh.chatappapi.model.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
