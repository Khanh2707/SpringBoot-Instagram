package com.tpkhanh.chatappapi.mapper;

import com.tpkhanh.chatappapi.dto.request.UserCreationRequest;
import com.tpkhanh.chatappapi.dto.request.UserUpdateInfoRequest;
import com.tpkhanh.chatappapi.dto.response.UserResponse;
import com.tpkhanh.chatappapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "account", ignore = true)
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUserInfo(@MappingTarget User user, UserUpdateInfoRequest request);
}
