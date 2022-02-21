package com.jochman.blog;

import com.jochman.components.entities.Blog;
import com.jochman.components.requestBodies.BlogCreationRequest;
import com.jochman.components.requestBodies.PostCreationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//todo: security staff
//todo: testing
//todo: create verification service

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/blogs")
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs(){
        log.info("get request for all blogs");
        return new ResponseEntity<>(blogService.getAllBlog(), HttpStatus.OK);
    }

    @PostMapping("/create/{bloggerId}")
    public ResponseEntity createBlog(@RequestBody BlogCreationRequest blogCreationRequest,@PathVariable("bloggerId") Long bloggerId){
        log.info("new blog created {}", blogCreationRequest);
        blogService.createBlog(blogCreationRequest, bloggerId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

//    @PutMapping("/{blogId}/post/add")
//    public ResponseEntity addPostToBlog(@RequestBody PostCreationRequest postCreationRequest, @PathVariable Long blogId){
//        log.info("new post created {} for blogger {}", postCreationRequest, blogId);
//        blogService.addPost(postCreationRequest, blogId);
//        return new ResponseEntity(HttpStatus.CREATED);
//    }

    @GetMapping(path = "{blogId}")
    public ResponseEntity<Blog> getBlog(@PathVariable Long blogId){
        log.info("get request for blog {}", blogId);
        return new ResponseEntity<>(blogService.getBlog(blogId), HttpStatus.OK);
    }
}
