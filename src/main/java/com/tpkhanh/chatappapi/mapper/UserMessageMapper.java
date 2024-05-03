package com.tpkhanh.chatappapi.mapper;

import com.tpkhanh.chatappapi.dto.request.UserMessageCreationRequest;
import com.tpkhanh.chatappapi.dto.request.UserMessageUpdateIsCheckRequest;
import com.tpkhanh.chatappapi.dto.request.UserUpdateInfoRequest;
import com.tpkhanh.chatappapi.dto.response.UserMessageResponse;
import com.tpkhanh.chatappapi.model.User;
import com.tpkhanh.chatappapi.model.UserMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMessageMapper {
    @Mapping(target = "user1", ignore = true)
    @Mapping(target = "user2", ignore = true)
    UserMessage toUserMessage(UserMessageCreationRequest request);

    UserMessageResponse toUserMessageResponse(UserMessage userMessage);

    @Mapping(target = "user1", ignore = true)
    @Mapping(target = "user2", ignore = true)
    void updateUserMessageIsCheck(@MappingTarget UserMessage userMessage, UserMessageUpdateIsCheckRequest request);
}
