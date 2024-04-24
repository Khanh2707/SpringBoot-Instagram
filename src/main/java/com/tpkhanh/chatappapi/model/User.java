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
public class User {
    @Id
    String idUser;
    String avatar;
    String name;
    String description;
    Boolean gender;
    Boolean stateActive;
    LocalDateTime lastTimeActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAccountUser", referencedColumnName = "idAccount")
    Account account;
}
