package com.jochman.blogger;

import com.jochman.clients.blogger.Blogger;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@ComponentScan("com.jochman.clients.blogger")
public class BloggerService {

    private final BloggerRepository bloggerRepository;

    public void registerBlogger(BloggerRegistrationRequest bloggerRegistrationRequest){
        Blogger blogger = Blogger.builder()
                .nickName(bloggerRegistrationRequest.nickName())
                .email(bloggerRegistrationRequest.email())
                .build();
        //todo: check if nickname isn't taken
        //todo: check if email is valid
        bloggerRepository.save(blogger);
    }

    public Optional<Blogger> getBlogger(Long bloggerId) {
        return bloggerRepository.findById(bloggerId);
    }
}
