package com.tpkhanh.chatappapi.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCommentPostCreationRequest {
    String id_user_user_comment_post;
    Integer id_comment_post_user_comment_post;
    Integer id_post_user_comment_post;
}
