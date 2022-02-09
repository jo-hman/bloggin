package com.jochman.post;

import com.jochman.clients.entities.Post;
import com.jochman.clients.requestBodies.PostCreationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/post")
public class PostController {

    private final PostService postService;

    //todo: create endpoint to retrieve all posts from db

    @GetMapping(path = "{postId}")
    public Post getPost(@PathVariable Long postId){
        log.info("post get request for post {}", postId);
        return postService.getPost(postId);
    }
}
