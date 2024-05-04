package com.tpkhanh.chatappapi.mapper;

import com.tpkhanh.chatappapi.dto.request.AccountUpdateRoleRequest;
import com.tpkhanh.chatappapi.dto.request.PostCreationRequest;
import com.tpkhanh.chatappapi.dto.request.PostNotificationCreationRequest;
import com.tpkhanh.chatappapi.dto.request.PostNotificationUpdateIsCheckRequest;
import com.tpkhanh.chatappapi.dto.response.PostNotificationResponse;
import com.tpkhanh.chatappapi.dto.response.PostResponse;
import com.tpkhanh.chatappapi.model.Account;
import com.tpkhanh.chatappapi.model.Post;
import com.tpkhanh.chatappapi.model.PostNotification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostNotificationMapper {
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "user1", ignore = true)
    @Mapping(target = "user2", ignore = true)
    @Mapping(target = "commentPost", ignore = true)
    PostNotification toPostNotification(PostNotificationCreationRequest request);

    PostNotificationResponse toPostNotificationResponse(PostNotification postNotification);

    @Mapping(target = "user2", ignore = true)
    void updatePostNotificationIsCheck(@MappingTarget PostNotification postNotification, PostNotificationUpdateIsCheckRequest request);
}
