package com.jochman.blog;

import com.jochman.clients.entities.Blog;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/blog")
public class BlogController {

    private final BlogService blogService;

    @PostMapping
    public void createBlog(@RequestBody BlogCreationRequest blogCreationRequest){
        log.info("new blog created {}", blogCreationRequest);
        blogService.createBlog(blogCreationRequest);
    }

    @PutMapping("/bloggers/{bloggerId}")
    public void addBloggerToBlog(@RequestBody BlogCreationRequest blogCreationRequest, @PathVariable Long bloggerId){
        log.info("new blog created {} for blogger {}", blogCreationRequest, bloggerId);
        blogService.addBloggerToBlog(blogCreationRequest, bloggerId);
    }

    @GetMapping(path = "{blogId}")
    public Blog getBlog(@PathVariable Long blogId){
        return blogService.getBlog(blogId);
    }
}
