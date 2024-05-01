package com.tpkhanh.chatappapi.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentPostResponse {
    Integer idCommentPost;
    String content;
    LocalDateTime dateTimeComment;
}
