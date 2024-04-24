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
public class SearchHistoryResponse {
    UserResponse user1;
    UserResponse user2;
    LocalDateTime dateTimeSearch;
}
