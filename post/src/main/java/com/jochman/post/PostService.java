package com.jochman.post;

import com.jochman.components.entities.Blogger;
import com.jochman.components.entities.Post;
import com.jochman.components.repositories.BlogRepository;
import com.jochman.components.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final BlogRepository blogRepository;

    public Post getPost(Long postId) {
        return postRepository.findById(postId).get();
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
