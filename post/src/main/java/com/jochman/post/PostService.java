package com.jochman.post;

import com.jochman.clients.entities.Blog;
import com.jochman.clients.entities.Post;
import com.jochman.clients.repositories.BlogRepository;
import com.jochman.clients.repositories.PostRepository;
import com.jochman.clients.requestBodies.PostCreationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final BlogRepository blogRepository;

    public Post getPost(Long postId) {
        return postRepository.findById(postId).get();
    }
}
