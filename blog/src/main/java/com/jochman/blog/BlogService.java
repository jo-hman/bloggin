package com.jochman.blog;

import com.jochman.components.entities.Blog;
import com.jochman.components.entities.Blogger;
import com.jochman.components.entities.Post;
import com.jochman.components.repositories.BlogRepository;
import com.jochman.components.repositories.BloggerRepository;
import com.jochman.components.requestBodies.BlogCreationRequest;
import com.jochman.components.requestBodies.PostCreationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }
}
