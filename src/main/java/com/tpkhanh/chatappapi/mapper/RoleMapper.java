package com.tpkhanh.chatappapi.mapper;

import com.tpkhanh.chatappapi.dto.request.PermissionRequest;
import com.tpkhanh.chatappapi.dto.request.RoleRequest;
import com.tpkhanh.chatappapi.dto.response.PermissionResponse;
import com.tpkhanh.chatappapi.dto.response.RoleResponse;
import com.tpkhanh.chatappapi.model.Permission;
import com.tpkhanh.chatappapi.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);
}
