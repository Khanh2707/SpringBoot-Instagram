package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.response.MediaPostResponse;
import com.tpkhanh.chatappapi.exception.AppException;
import com.tpkhanh.chatappapi.exception.ErrorCode;
import com.tpkhanh.chatappapi.mapper.MediaPostMapper;
import com.tpkhanh.chatappapi.model.MediaPost;
import com.tpkhanh.chatappapi.model.Post;
import com.tpkhanh.chatappapi.model.UserLikePost;
import com.tpkhanh.chatappapi.repository.MediaPostRepository;
import com.tpkhanh.chatappapi.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class MediaPostService {

    MediaPostMapper mediaPostMapper;

    MediaPostRepository mediaPostRepository;

    PostRepository postRepository;

    CloudinaryService cloudinaryService;

    public MediaPostResponse createMediaPost(MultipartFile fileMedia, String type, Integer post) throws IOException {

        Map r = cloudinaryService.uploadFile(fileMedia, "post/"+post, "");

        String url = (String) r.get("secure_url");

        MediaPost mediaPost = new MediaPost();
        mediaPost.setUrl(url);
        mediaPost.setType(type);

        Post postObject = postRepository.findById(post).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        mediaPost.setPost(postObject);

        return mediaPostMapper.toMediaPostResponse(mediaPostRepository.save(mediaPost));
    }

    public void deleteAllMediaPostByPost(Integer idPost) throws IOException {
        List<MediaPost> mediaPosts = mediaPostRepository.findByPost_IdPost(idPost);

        for (MediaPost mediaPost : mediaPosts) {
            cloudinaryService.destroyFile("post/"+mediaPost.getIdMediaPost(), mediaPost.getUrl());
        }

        mediaPostRepository.deleteAll(mediaPosts);
    }
}
