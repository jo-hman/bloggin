package com.jochman.blog;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public void createBlog(BlogCreationRequest blogCreationRequest){
        Blog blog = Blog.builder()
                .blogName(blogCreationRequest.blogName())
                .blogDescription(blogCreationRequest.blogDescription())
                .build();
        //todo: check if blogName isn't taken
        blogRepository.save(blog);
    }

    public Optional<Blog> getBlog(Long blogId) {
        return blogRepository.findById(blogId);
    }
}
