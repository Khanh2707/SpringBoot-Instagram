package com.tpkhanh.chatappapi.dto.response;

import com.tpkhanh.chatappapi.model.Post;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MediaPostResponse {
    Integer idMediaPost;
    String url;
    String type;
}
