package com.tpkhanh.chatappapi.dto.response;

import com.tpkhanh.chatappapi.model.Permission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    String name;
    String description;
    Set<Permission> permissions;
}
