package com.jochman.post;

import com.jochman.clients.entities.Post;
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
    public void createPost(@RequestBody PostCreationRequest postCreationRequest){
      log.info("new post created {}", postCreationRequest);
      postService.createPost(postCreationRequest);
    }

    @PutMapping("/blogs/{blogId}")
    public void addPostToBlog(@RequestBody PostCreationRequest postCreationRequest, @PathVariable Long blogId){
        log.info("new blog created {} for blogger {}", postCreationRequest, blogId);
        postService.addPostToBlog(postCreationRequest, blogId);
    }

    @GetMapping(path = "{postId}")
    public Post getPost(@PathVariable Long postId){
        log.info("post get request for post {}", postId);
        return postService.getPost(postId);
    }
}
