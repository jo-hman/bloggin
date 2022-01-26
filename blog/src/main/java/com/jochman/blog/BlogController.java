package com.jochman.blog;

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
    public void createBlog(BlogCreationRequest blogCreationRequest){
        log.info("new blog created {}", blogCreationRequest);
        blogService.createBlog(blogCreationRequest);
    }

    @GetMapping(path = "{blogId}")
    public Optional<Blog> getBlog(@PathVariable Long blogId){
        return blogService.getBlog(blogId);
    }
}
