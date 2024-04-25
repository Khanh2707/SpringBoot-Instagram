package com.tpkhanh.chatappapi.dto.response;

import com.tpkhanh.chatappapi.model.Account;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogLockAccountResponse {
    Integer idLogLockAccount;
    String reasonLock;
    Boolean stateLock;
    LocalDateTime dateTimeLock;
}
