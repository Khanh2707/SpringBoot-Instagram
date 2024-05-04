package com.tpkhanh.chatappapi.dto.response;

import com.tpkhanh.chatappapi.model.CommentPost;
import com.tpkhanh.chatappapi.model.Post;
import com.tpkhanh.chatappapi.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostNotificationResponse {
    Integer idPostNotification;
    String action;
    Boolean isCheck;
    LocalDateTime dateTimeNotification;
    PostResponse post;
    UserResponse user1;
    UserResponse user2;
    CommentPostResponse commentPost;
}
