package com.tpkhanh.chatappapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class CommentPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCommentPost;
    String content;
    LocalDateTime dateTimeComment;
}
