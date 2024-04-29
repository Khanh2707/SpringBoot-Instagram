package com.tpkhanh.chatappapi.dto.response;

import com.tpkhanh.chatappapi.model.Post;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String idUser;
    String avatar;
    String name;
    String description;
    Boolean gender;
    Boolean stateActive;
    LocalDateTime lastTimeActive;
    List<PostResponse> posts;
}
