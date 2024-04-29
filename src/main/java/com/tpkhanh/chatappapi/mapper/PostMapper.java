package com.tpkhanh.chatappapi.mapper;

import com.tpkhanh.chatappapi.dto.request.PostCreationRequest;
import com.tpkhanh.chatappapi.dto.response.PostResponse;
import com.tpkhanh.chatappapi.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "user", ignore = true)
    Post toPost(PostCreationRequest request);

    PostResponse toPostResponse(Post post);
}
