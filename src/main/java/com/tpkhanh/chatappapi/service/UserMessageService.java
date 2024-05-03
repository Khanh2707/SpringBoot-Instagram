package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.request.SearchHistoryCreationRequest;
import com.tpkhanh.chatappapi.dto.request.UserMessageCreationRequest;
import com.tpkhanh.chatappapi.dto.response.SearchHistoryResponse;
import com.tpkhanh.chatappapi.dto.response.UserMessageResponse;
import com.tpkhanh.chatappapi.dto.response.UserResponse;
import com.tpkhanh.chatappapi.exception.AppException;
import com.tpkhanh.chatappapi.exception.ErrorCode;
import com.tpkhanh.chatappapi.mapper.UserMapper;
import com.tpkhanh.chatappapi.mapper.UserMessageMapper;
import com.tpkhanh.chatappapi.model.SearchHistory;
import com.tpkhanh.chatappapi.model.SearchHistoryKey;
import com.tpkhanh.chatappapi.model.User;
import com.tpkhanh.chatappapi.model.UserMessage;
import com.tpkhanh.chatappapi.repository.UserMessageRepository;
import com.tpkhanh.chatappapi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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

        userMessage.setDateTimeMessage(LocalDateTime.now().withNano(0));
        userMessage.setIsRecall(false);

        User user1 = userRepository.findById(request.getUser1()).orElseThrow(() -> new AppException(ErrorCode.ID_USER_NOT_EXISTED));
        userMessage.setUser1(user1);

        User user2 = userRepository.findById(request.getUser2()).orElseThrow(() -> new AppException(ErrorCode.ID_USER_NOT_EXISTED));
        userMessage.setUser2(user2);

        return userMessageMapper.toUserMessageResponse(userMessageRepository.save(userMessage));
    }

    public void deleteById(Integer idUserMessage) {
        userMessageRepository.deleteById(idUserMessage);
    }
}
