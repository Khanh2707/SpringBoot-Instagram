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
public class MediaPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idMediaPost;
    String url;
    String type;

    @ManyToOne
    @JoinColumn(name = "idPostMediaPost", referencedColumnName = "idPost")
    Post post;
}
