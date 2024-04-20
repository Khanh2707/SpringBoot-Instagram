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
    Integer id_account;
    String account;
    String password;
    LocalDateTime date_time_create;
    Boolean state_active;
    LocalDateTime last_time_active;

    @OneToOne(mappedBy = "account")
    User user;

    @ManyToMany
    Set<Role> roles;
}
