package com.tpkhanh.chatappapi.mapper;

import com.tpkhanh.chatappapi.dto.request.UserCommentPostCreationRequest;
import com.tpkhanh.chatappapi.dto.response.UserCommentPostResponse;
import com.tpkhanh.chatappapi.model.UserCommentPost;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCommentPostMapper {
    UserCommentPost toUserCommentPost(UserCommentPostCreationRequest request);

    UserCommentPostResponse toUserCommentPostResponse(UserCommentPost userCommentPost);
}
