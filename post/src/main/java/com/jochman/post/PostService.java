package com.jochman.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void createPost(PostCreationRequest postCreationRequest){
        Post post = Post.builder()
                .postTitle(postCreationRequest.postTitle())
                .postContent(postCreationRequest.postContent())
                .build();
        //todo:check if the title isn't taken
        postRepository.save(post);
    }

    public Optional<Post> getPost(Long postId) {
        return postRepository.findById(postId);
    }
}
