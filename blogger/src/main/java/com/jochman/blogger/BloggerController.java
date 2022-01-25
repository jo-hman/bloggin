package com.jochman.blogger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/bloggers")
public class BloggerController {

    private BloggerService bloggerService;

    @PostMapping
    public void registerBlogger(@RequestBody BloggerRegistrationRequest bloggerRegistrationRequest){
        log.info("new blogger registered {}", bloggerRegistrationRequest);
        bloggerService.registerBlogger(bloggerRegistrationRequest);
    }
}
