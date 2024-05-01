package com.tpkhanh.chatappapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class UserCommentPostKey implements Serializable {
    @Column(name = "id_user_user_comment_post")
    String idUser;

    @Column(name = "id_comment_post_user_comment_post")
    Integer idCommentPost;

    @Column(name = "id_post_user_comment_post")
    Integer idPost;
}
