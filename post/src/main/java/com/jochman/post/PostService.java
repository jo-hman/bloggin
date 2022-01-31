package com.jochman.post;

import com.jochman.clients.entities.Post;
import com.jochman.clients.repositories.BlogRepository;
import com.jochman.clients.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final BlogRepository blogRepository;

    public void createPost(PostCreationRequest postCreationRequest){
        Post post = Post.builder()
                .postTitle(postCreationRequest.postTitle())
                .postContent(postCreationRequest.postContent())
                .build();
        //todo:check if the title isn't taken
        postRepository.save(post);
    }

    public void addPostToBlog(PostCreationRequest postCreationRequest, Long blogId){
        Post blog = Post.builder()
                .blog(blogRepository.findById(blogId).get())
                .postTitle(postCreationRequest.postTitle())
                .postContent(postCreationRequest.postContent())
                .build();
        postRepository.save(blog);
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId).get();
    }
}
