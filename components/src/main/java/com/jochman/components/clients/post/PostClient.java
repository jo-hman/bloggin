package com.jochman.components.clients.post;


import com.jochman.components.entities.Post;
import com.jochman.components.requestBodies.PostCreationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "post"
)
public interface PostClient {
    //TODO consider this
    @GetMapping("/api/v1/posts")
    List<Post> getAllPosts();

    @PostMapping("/api/v1/posts/add/{blogId}")
    ResponseEntity createPost(@RequestBody PostCreationRequest postCreationRequest, @PathVariable("blogId") Long blogId);
}
