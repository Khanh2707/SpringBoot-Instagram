package com.tpkhanh.chatappapi.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
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

    @OneToOne(mappedBy = "account")
    User user;

    @OneToMany(mappedBy = "account")
    List<LogLockAccount> logLockAccounts;

    @ManyToMany
    Set<Role> roles;
}
