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
public class UserMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idUserMessage;
    String message;
    LocalDateTime dateTimeMessage;
    Boolean isRecall;

    @ManyToOne
    @JoinColumn(name = "id_user_user_message_1")
    User user1;

    @ManyToOne
    @JoinColumn(name = "id_user_user_message_2")
    User user2;
}
