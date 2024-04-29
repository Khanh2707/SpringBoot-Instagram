package com.tpkhanh.chatappapi.mapper;

import com.tpkhanh.chatappapi.dto.response.MediaPostResponse;
import com.tpkhanh.chatappapi.model.MediaPost;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MediaPostMapper {

    MediaPostResponse toMediaPostResponse(MediaPost mediaPost);
}
