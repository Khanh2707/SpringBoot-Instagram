package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

}
