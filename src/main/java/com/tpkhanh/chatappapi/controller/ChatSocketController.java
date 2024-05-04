package com.tpkhanh.chatappapi.controller;

import com.tpkhanh.chatappapi.model.MessageSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/private-message")
    public MessageSocket receivePrivateMessage(@Payload MessageSocket messageSocket) {

        simpMessagingTemplate.convertAndSendToUser(messageSocket.getReceiverName(), "/private", messageSocket);
        return messageSocket;
    }

    @MessageMapping("/notification")
    public MessageSocket receiveNotification(@Payload MessageSocket messageSocket) {
        simpMessagingTemplate.convertAndSendToUser(messageSocket.getReceiverName(), "/notification", messageSocket);
        return messageSocket;
    }
}
