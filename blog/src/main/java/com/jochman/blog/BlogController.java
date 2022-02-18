package com.jochman.blog;

import com.jochman.components.entities.Blog;
import com.jochman.components.requestBodies.BlogCreationRequest;
import com.jochman.components.requestBodies.PostCreationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//todo: security staff
//todo: testing

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/blogs")
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public List<Blog> getAllBlogs(){
        log.info("get request for all blogs");
        return blogService.getAllBlog();
    }

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
