package com.jochman.components.clients.post;


import com.jochman.components.entities.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "post"
)
public interface PostClient {
    @GetMapping("/api/v1/posts")
    List<Post> getAllPosts();
}
