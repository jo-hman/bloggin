package com.jochman.components.clients.blog;

import com.jochman.components.requestBodies.BlogCreationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(
        name = "blog"
)
public interface BlogClient {
    @PostMapping("api/v1/blogs/create/{bloggerId}")
    ResponseEntity createBlog(@RequestBody BlogCreationRequest blogCreationRequest, @PathVariable("bloggerId") Long bloggerId);
}
