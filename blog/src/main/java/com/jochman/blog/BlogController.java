package com.jochman.blog;

import com.jochman.clients.entities.Blog;
import com.jochman.clients.requestBodies.BlogCreationRequest;
import com.jochman.clients.requestBodies.PostCreationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/blog")
public class BlogController {

    private final BlogService blogService;

    //todo: create endpoint to retrieve all blogs from db

    @PostMapping
    public void createBlog(@RequestBody BlogCreationRequest blogCreationRequest){
        log.info("new blog created {}", blogCreationRequest);
        blogService.createBlog(blogCreationRequest);
    }

    @PutMapping("/{blogId}/post/add")
    public void addPostToBlog(@RequestBody PostCreationRequest postCreationRequest, @PathVariable Long blogId){
        log.info("new post created {} for blogger {}", postCreationRequest, blogId);
        blogService.addPost(postCreationRequest, blogId);
    }

    @GetMapping(path = "{blogId}")
    public Blog getBlog(@PathVariable Long blogId){
        return blogService.getBlog(blogId);
    }
}
