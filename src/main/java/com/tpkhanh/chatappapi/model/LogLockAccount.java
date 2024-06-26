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
public class LogLockAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idLogLockAccount;
    String reasonLock;
    Boolean stateLock;
    LocalDateTime dateTimeLock;

    @ManyToOne
    @JoinColumn(name = "idAccountLogLockAccount", referencedColumnName = "idAccount")
    Account account;

}
