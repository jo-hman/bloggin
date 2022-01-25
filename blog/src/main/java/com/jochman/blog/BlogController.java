package com.jochman.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/blog")
public class BlogController {

    private BlogService blogService;

    @PostMapping
    public void createBlog(BlogCreationRequest blogCreationRequest){
        log.info("new blog created {}", blogCreationRequest);
        blogService.createBlog(blogCreationRequest);
    }
}
