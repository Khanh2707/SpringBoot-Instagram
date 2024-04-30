package com.tpkhanh.chatappapi.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class UserLikePost {
    @EmbeddedId
    UserLikePostKey id;

    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "id_user_user_like_post")
    User user;

    @ManyToOne
    @MapsId("idPost")
    @JoinColumn(name = "id_post_user_like_post")
    Post post;

    LocalDateTime dateTimeLike;
}
