package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.request.RoleRequest;
import com.tpkhanh.chatappapi.dto.response.RoleResponse;
import com.tpkhanh.chatappapi.mapper.RoleMapper;
import com.tpkhanh.chatappapi.repository.PermissionRepository;
import com.tpkhanh.chatappapi.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse createRole(RoleRequest roleRequest) {
        var role = roleMapper.toRole(roleRequest);

        var permissions = permissionRepository.findAllById(roleRequest.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void deleteRole(String roleId) {
        roleRepository.deleteById(roleId);
    }
}
