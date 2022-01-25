package com.jochman.post;

import org.springframework.stereotype.Service;

@Service
public class PostService {

    PostRepository postRepository;

    public void createPost(PostCreationRequest postCreationRequest){
        Post post = Post.builder()
                .postTitle(postCreationRequest.postTitle())
                .postContent(postCreationRequest.postContent())
                .build();
        //todo:check if the title isn't taken
        postRepository.save(post);
    }
}
