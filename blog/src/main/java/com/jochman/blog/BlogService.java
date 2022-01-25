package com.jochman.blog;

import org.springframework.stereotype.Service;

@Service
public class BlogService {

    private BlogRepository blogRepository;

    public void createBlog(BlogCreationRequest blogCreationRequest){
        Blog blog = Blog.builder()
                .blogName(blogCreationRequest.blogName())
                .blogDescription(blogCreationRequest.blogDescription())
                .build();
        //todo: check if blogName isn't taken
        blogRepository.save(blog);
    }
}
