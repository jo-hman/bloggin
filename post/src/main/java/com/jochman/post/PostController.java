package com.jochman.post;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public void createPost(PostCreationRequest postCreationRequest){
      log.info("new post created {}", postCreationRequest);
      postService.createPost(postCreationRequest);
    }

    @GetMapping(path = "{postId}")
    public Optional<Post> getPost(@PathVariable Long postId){
        log.info("post get request for post {}", postId);
        return postService.getPost(postId);
    }
}
