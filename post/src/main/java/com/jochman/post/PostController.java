package com.jochman.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/post")
public class PostController {

    PostService postService;

    @PostMapping
    public void createPost(PostCreationRequest postCreationRequest){
      log.info("new post created {}", postCreationRequest);
      postService.createPost(postCreationRequest);
    }
}