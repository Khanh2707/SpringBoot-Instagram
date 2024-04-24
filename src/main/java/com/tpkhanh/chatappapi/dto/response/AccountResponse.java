package com.tpkhanh.chatappapi.dto.response;

import com.tpkhanh.chatappapi.model.User;
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
    Integer idAccount;
    String account;
    LocalDateTime dateTimeCreate;
    UserResponse user;
    Set<RoleResponse> roles;
}
