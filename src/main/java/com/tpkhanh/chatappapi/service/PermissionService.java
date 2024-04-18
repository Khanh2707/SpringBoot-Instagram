package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.request.PermissionRequest;
import com.tpkhanh.chatappapi.dto.response.PermissionResponse;
import com.tpkhanh.chatappapi.mapper.PermissionMapper;
import com.tpkhanh.chatappapi.model.Permission;
import com.tpkhanh.chatappapi.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {
    PermissionRepository permissionRepository;

    PermissionMapper permissionMapper;

    public PermissionResponse createPermission(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);

        permission = permissionRepository.save(permission);

        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAllPermissions() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void deletePermission(String permissionId) {
        permissionRepository.deleteById(permissionId);
    }
}
