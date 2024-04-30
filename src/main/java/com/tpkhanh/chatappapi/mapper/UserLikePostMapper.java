package com.tpkhanh.chatappapi.mapper;

import com.tpkhanh.chatappapi.dto.request.UserLikePostCreationRequest;
import com.tpkhanh.chatappapi.dto.response.UserLikePostResponse;
import com.tpkhanh.chatappapi.model.UserLikePost;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserLikePostMapper {
    UserLikePost toUserLikePost(UserLikePostCreationRequest request);

    UserLikePostResponse toUserLikePostResponse(UserLikePost userLikePost);
}
