package com.jochman.blog;

import com.jochman.clients.entities.Blog;
import com.jochman.clients.repositories.BlogRepository;
import com.jochman.clients.repositories.BloggerRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final BloggerRepository bloggerRepository;

    public void createBlog(BlogCreationRequest blogCreationRequest){
        Blog blog = Blog.builder()
                .blogName(blogCreationRequest.blogName())
                .blogDescription(blogCreationRequest.blogDescription())
                .build();
        //todo: check if blogName isn't taken
        blogRepository.save(blog);
    }

    public void addBloggerToBlog(BlogCreationRequest blogCreationRequest, Long bloggerId){
        Blog blog = Blog.builder()
                .blogger(bloggerRepository.findById(bloggerId).get())
                .blogName(blogCreationRequest.blogName())
                .blogDescription(blogCreationRequest.blogDescription())
                .build();
        blogRepository.save(blog);
    }

    public Blog getBlog(Long blogId) {
        return blogRepository.findById(blogId).get();
    }
}
