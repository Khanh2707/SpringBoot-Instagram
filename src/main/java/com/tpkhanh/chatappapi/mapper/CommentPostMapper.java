package com.tpkhanh.chatappapi.mapper;

import com.tpkhanh.chatappapi.dto.request.CommentPostCreationRequest;
import com.tpkhanh.chatappapi.dto.response.CommentPostResponse;
import com.tpkhanh.chatappapi.dto.response.UserResponse;
import com.tpkhanh.chatappapi.model.CommentPost;
import com.tpkhanh.chatappapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentPostMapper {
    CommentPost toCommentPost(CommentPostCreationRequest request);

    CommentPostResponse toCommentPostResponse(CommentPost commentPost);
}
