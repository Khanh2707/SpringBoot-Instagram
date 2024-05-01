package com.tpkhanh.chatappapi.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class UserCommentPost {
    @EmbeddedId
    UserCommentPostKey id;

    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "id_user_user_comment_post")
    User user;

    @ManyToOne
    @MapsId("idCommentPost")
    @JoinColumn(name = "id_comment_post_user_comment_post")
    CommentPost commentPost;

    @ManyToOne
    @MapsId("idPost")
    @JoinColumn(name = "id_post_user_comment_post")
    Post post;
}
