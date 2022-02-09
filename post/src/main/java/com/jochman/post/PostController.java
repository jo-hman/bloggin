package com.jochman.post;

import com.jochman.components.entities.Post;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<Post> getAllBloggers(){
        log.info("get request for all bloggers");
        return postService.getAllPosts();
    }

    @GetMapping(path = "{postId}")
    public Post getPost(@PathVariable Long postId){
        log.info("post get request for post {}", postId);
        return postService.getPost(postId);
    }
}
