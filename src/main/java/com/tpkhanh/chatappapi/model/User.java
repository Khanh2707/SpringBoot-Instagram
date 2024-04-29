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
public class User {
    @Id
    String idUser;
    String avatar;
    String name;
    String description;
    Boolean gender;
    Boolean stateActive;
    LocalDateTime lastTimeActive;

    @OneToOne
    @JoinColumn(name = "idAccountUser", referencedColumnName = "idAccount")
    Account account;

    @OneToMany(mappedBy = "user")
    List<Post> posts;
}
