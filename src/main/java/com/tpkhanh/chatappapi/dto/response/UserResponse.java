package com.tpkhanh.chatappapi.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String idUser;
    String avatar;
    String name;
    String description;
    Boolean gender;
    AccountResponse account;
}
