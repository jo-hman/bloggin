package com.jochman.blog;

import com.jochman.clients.entities.Blog;
import com.jochman.clients.entities.Post;
import com.jochman.clients.repositories.BlogRepository;
import com.jochman.clients.repositories.BloggerRepository;
import com.jochman.clients.requestBodies.BlogCreationRequest;
import com.jochman.clients.requestBodies.PostCreationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


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

    public void addPost(PostCreationRequest postCreationRequest, Long blogId){
        Blog blog = blogRepository.findById(blogId).get();

        Post post = Post.builder()
                .blog(blog)
                .postTitle(postCreationRequest.postTitle())
                .postContent(postCreationRequest.postContent())
                .build();

        //todo:check if post's title isn't taken
        blog.addPost(post);
        blogRepository.save(blog);
    }

    public Blog getBlog(Long blogId) {
        return blogRepository.findById(blogId).get();
    }
}
