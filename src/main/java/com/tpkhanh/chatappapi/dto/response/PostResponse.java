package com.tpkhanh.chatappapi.dto.response;

import com.tpkhanh.chatappapi.model.MediaPost;
import com.tpkhanh.chatappapi.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {
    Integer idPost;
    String caption;
    LocalDateTime dateTimeCreate;
    List<MediaPostResponse> mediaPosts;
}
