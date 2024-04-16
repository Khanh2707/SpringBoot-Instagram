package com.tpkhanh.chatappapi.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
    Integer id_account;
    String account;
    String password;
    LocalDateTime date_time_create;
    Boolean state_active;
    LocalDateTime last_time_active;
}
