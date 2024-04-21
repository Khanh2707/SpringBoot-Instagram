package com.tpkhanh.chatappapi.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idAccount;
    String account;
    String password;
    LocalDateTime dateTimeCreate;
    Boolean stateActive;
    LocalDateTime lastTimeActive;

    @OneToOne(mappedBy = "account")
    User user;

    @ManyToMany
    Set<Role> roles;
}
