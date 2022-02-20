package com.jochman.post;

import com.jochman.components.entities.Post;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        log.info("get request for all posts");
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping(path = "{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId){
        log.info("post get request for post {}", postId);
        return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
    }
}
