package com.tpkhanh.chatappapi.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserMessageUpdateIsCheckRequest {
    String user1;
    String user2;
}
