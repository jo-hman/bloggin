package com.jochman.blogger;

import com.jochman.clients.entities.Blog;
import com.jochman.clients.entities.Blogger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/bloggers")
public class BloggerController {

    private final BloggerService bloggerService;

    @PostMapping
    public void registerBlogger(@RequestBody BloggerRegistrationRequest bloggerRegistrationRequest){
        log.info("new blogger registered {}", bloggerRegistrationRequest);
        bloggerService.registerBlogger(bloggerRegistrationRequest);
    }

    @GetMapping(path = "{bloggerId}")
    public Blogger getBlogger(@PathVariable("bloggerId") Long bloggerId){
        log.info("blogger get request for blogger {}", bloggerId);
        return bloggerService.getBlogger(bloggerId);
    }

    @GetMapping(path = "{bloggerId}/blogs")
    public Set<Blog> getBlogs(@PathVariable("bloggerId") Long bloggerId){
        log.info("blogger {} get request for blogs", bloggerId);
        return bloggerService.getBlogs(bloggerId);
    }
}
