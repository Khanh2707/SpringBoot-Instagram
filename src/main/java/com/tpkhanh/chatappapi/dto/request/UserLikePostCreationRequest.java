package com.tpkhanh.chatappapi.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLikePostCreationRequest {
    String id_user_user_like_post;
    Integer id_post_user_like_post;
}
