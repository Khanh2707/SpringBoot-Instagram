package com.tpkhanh.chatappapi.dto.response;

import com.tpkhanh.chatappapi.model.CommentPost;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCommentPostResponse {
    UserResponse user;
    CommentPost commentPost;
    PostResponse post;
}
