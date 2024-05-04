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
public class PostNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idPostNotification;
    String action;
    Boolean isCheck;
    LocalDateTime dateTimeNotification;

    @ManyToOne
    @JoinColumn(name = "id_post_post_notification")
    Post post;

    @ManyToOne
    @JoinColumn(name = "id_user_1_post_notification")
    User user1;

    @ManyToOne
    @JoinColumn(name = "id_user_2_post_notification")
    User user2;

    @ManyToOne
    @JoinColumn(name = "id_comment_post_post_notification")
    CommentPost commentPost;
}
