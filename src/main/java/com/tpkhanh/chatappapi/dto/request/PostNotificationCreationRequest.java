package com.tpkhanh.chatappapi.dto.request;

import com.tpkhanh.chatappapi.model.CommentPost;
import com.tpkhanh.chatappapi.model.Post;
import com.tpkhanh.chatappapi.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostNotificationCreationRequest {
    String action;
    Integer post;
    String user1;
    String user2;
    Integer commentPost;
}
