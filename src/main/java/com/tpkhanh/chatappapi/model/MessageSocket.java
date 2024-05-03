package com.tpkhanh.chatappapi.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageSocket {
    String message;
    String senderName;
    String receiverName;
}
