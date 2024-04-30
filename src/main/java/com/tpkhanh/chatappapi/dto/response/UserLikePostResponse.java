package com.tpkhanh.chatappapi.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLikePostResponse {
    UserResponse user;
    PostResponse post;
    LocalDateTime dateTimeLike;
}
