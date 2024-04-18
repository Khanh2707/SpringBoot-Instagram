package com.tpkhanh.chatappapi.dto.response;

import com.tpkhanh.chatappapi.model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
    Integer id_account;
    String account;
    LocalDateTime date_time_create;
    Boolean state_active;
    LocalDateTime last_time_active;
    Set<RoleResponse> roles;
}
