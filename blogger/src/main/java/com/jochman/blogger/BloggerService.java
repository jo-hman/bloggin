package com.jochman.blogger;

import com.jochman.components.requestBodies.BlogCreationRequest;
import com.jochman.components.entities.Blog;
import com.jochman.components.entities.Blogger;
import com.jochman.components.repositories.BloggerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
//@ComponentScan("com.jochman.clients.blogger")
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

    public void addBlog(BlogCreationRequest blogCreationRequest, Long bloggerId){
        Blogger blogger = bloggerRepository.findById(bloggerId).get();
        Blog blog = Blog.builder()
                .blogger(blogger)
                .blogName(blogCreationRequest.blogName())
                .blogDescription(blogCreationRequest.blogDescription())
                .build();
        blogger.addBlog(blog);
        bloggerRepository.save(blogger);
    }

    public Blogger getBlogger(Long bloggerId) {
        return bloggerRepository.findById(bloggerId).get();
    }

    public Set<Blog> getBlogs(Long bloggerId) {
        Blogger blogger = bloggerRepository.findById(bloggerId).get();
        return blogger.getBlogSet();
    }

    public List<Blogger> getAllBloggers() {
        return bloggerRepository.findAll();
    }
}
