package com.jochman.blogger;

import com.jochman.components.requestBodies.BlogCreationRequest;
import com.jochman.components.entities.Blog;
import com.jochman.components.entities.Blogger;
import com.jochman.components.requestBodies.PostCreationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/bloggers")
public class BloggerController {

    private final BloggerService bloggerService;

    @GetMapping
    public ResponseEntity<List<Blogger>> getAllBloggers(){
        log.info("get request for all bloggers");
        return new ResponseEntity<>(bloggerService.getAllBloggers(), HttpStatus.OK);
    }

    @GetMapping("{bloggerId}")
    public ResponseEntity<Blogger> getBlogger(@PathVariable("bloggerId") Long bloggerId){
        log.info("blogger get request for blogger {}", bloggerId);
        return new ResponseEntity<>(bloggerService.getBlogger(bloggerId), HttpStatus.OK);
    }

    @GetMapping("{bloggerId}/blogs")
    public ResponseEntity<Set<Blog>> getBlogs(@PathVariable("bloggerId") Long bloggerId){
        log.info("blogger {} get request for blogs", bloggerId);
        return new ResponseEntity<>(bloggerService.getBlogs(bloggerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity registerBlogger(@RequestBody BloggerRegistrationRequest bloggerRegistrationRequest){
        log.info("new blogger registered {}", bloggerRegistrationRequest);
        bloggerService.registerBlogger(bloggerRegistrationRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{bloggerId}/blog/add")
    public ResponseEntity createBlog(@RequestBody BlogCreationRequest blogCreationRequest, @PathVariable("bloggerId") Long bloggerId){
        log.info("new blog creation request for blogger {}", bloggerId);
        return bloggerService.addBlog(blogCreationRequest, bloggerId);
    }

    @PutMapping("/{bloggerId}/blog/{blogId}/post/add")
    public ResponseEntity createPost(@RequestBody PostCreationRequest postCreationRequest,
                                  @PathVariable("bloggerId") Long bloggerId,
                                  @PathVariable("blogId") Long blogId){
        log.info("new post creation request for blogger {} in blog {}", bloggerId, blogId);
        return bloggerService.createPost(postCreationRequest, bloggerId, blogId);
    }

    @DeleteMapping("/{bloggerId}")
    public ResponseEntity deleteBlogger(@PathVariable Long bloggerId){
        log.info("delete blogger {} request", bloggerId);
        bloggerService.deleteBlogger(bloggerId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


}
