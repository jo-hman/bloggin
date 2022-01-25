package com.jochman.blogger;

import org.springframework.stereotype.Service;

@Service
public class BloggerService {

    BloggerRepository bloggerRepository;

    public void registerBlogger(BloggerRegistrationRequest bloggerRegistrationRequest){
        Blogger blogger = Blogger.builder()
                .nickName(bloggerRegistrationRequest.nickName())
                .email(bloggerRegistrationRequest.email())
                .build();
        //todo: check if nickname isn't taken
        //todo: check if email is valid
        bloggerRepository.save(blogger);
    }
}
