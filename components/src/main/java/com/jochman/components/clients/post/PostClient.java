package com.jochman.components.clients.post;


import com.jochman.components.entities.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "post",
        url = "localhost:8082/api/v1/posts"
)
public interface PostClient {
    @GetMapping
    List<Post> getAllPosts();
}
