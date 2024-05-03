package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.request.UserMessageCreationRequest;
import com.tpkhanh.chatappapi.dto.request.UserMessageUpdateIsCheckRequest;
import com.tpkhanh.chatappapi.dto.response.UserMessageResponse;
import com.tpkhanh.chatappapi.exception.AppException;
import com.tpkhanh.chatappapi.exception.ErrorCode;
import com.tpkhanh.chatappapi.mapper.UserMessageMapper;
import com.tpkhanh.chatappapi.model.User;
import com.tpkhanh.chatappapi.model.UserMessage;
import com.tpkhanh.chatappapi.repository.UserMessageRepository;
import com.tpkhanh.chatappapi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserMessageService {

    UserMessageMapper userMessageMapper;

    UserRepository userRepository;

    UserMessageRepository userMessageRepository;

    public Integer countUncheckedMessagesForUser(String idUser1) {
        return userMessageRepository.countUncheckedMessagesForUser(idUser1);
    }

    public List<UserMessageResponse> findRecentUserMessagesWithOtherUsers(String idUser1) {
        return userMessageRepository.findRecentUserMessagesWithOtherUsers(idUser1).stream()
                .map(userMessageMapper::toUserMessageResponse)
                .toList();
    }

    public List<UserMessageResponse> getMessagesWithOtherUser(String idUser1, String idUser2) {
        return userMessageRepository.findLatestMessageBetweenUsers(idUser1, idUser2).stream()
                .map(userMessageMapper::toUserMessageResponse)
                .toList();
    }

    public UserMessageResponse createUserMessage(UserMessageCreationRequest request) {

        UserMessage userMessage = userMessageMapper.toUserMessage(request);

        userMessage.setIsCheck(false);
        userMessage.setDateTimeMessage(LocalDateTime.now().withNano(0));

        User user1 = userRepository.findById(request.getUser1()).orElseThrow(() -> new AppException(ErrorCode.ID_USER_NOT_EXISTED));
        userMessage.setUser1(user1);

        User user2 = userRepository.findById(request.getUser2()).orElseThrow(() -> new AppException(ErrorCode.ID_USER_NOT_EXISTED));
        userMessage.setUser2(user2);

        return userMessageMapper.toUserMessageResponse(userMessageRepository.save(userMessage));
    }

    public List<UserMessageResponse> updateUserMessageIsCheck(UserMessageUpdateIsCheckRequest request) {
        List<UserMessage> userMessages = userMessageRepository.findLatestMessageBetweenUsers(request.getUser1(), request.getUser2());

        for (UserMessage userMessage : userMessages) {
            userMessage.setIsCheck(true);
        }

        List<UserMessage> updatedMessages = userMessageRepository.saveAll(userMessages);

        return updatedMessages.stream()
                .map(userMessageMapper::toUserMessageResponse)
                .toList();
    }

    public void deleteById(Integer idUserMessage) {
        userMessageRepository.deleteById(idUserMessage);
    }
}
