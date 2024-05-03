package com.tpkhanh.chatappapi.dto.response;

import com.tpkhanh.chatappapi.model.User;
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
public class UserMessageResponse {
    Integer idUserMessage;
    String message;
    Boolean isCheck;
    LocalDateTime dateTimeMessage;
    UserResponse user1;
    UserResponse user2;
}
