package com.tpkhanh.chatappapi.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idPost;
    String caption;
    LocalDateTime dateTimeCreate;

    @OneToMany(mappedBy = "post")
    List<MediaPost> mediaPosts;

    @ManyToOne
    @JoinColumn(name = "idUserPost", referencedColumnName = "idUser")
    User user;
}
