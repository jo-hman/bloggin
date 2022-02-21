package com.jochman.blog;

import com.jochman.amqp.RabbitMQMessageProducer;
import com.jochman.components.entities.Blog;
import com.jochman.components.entities.Post;
import com.jochman.components.repositories.BlogRepository;
import com.jochman.components.repositories.BloggerRepository;
import com.jochman.components.repositories.PostRepository;
import com.jochman.components.requestBodies.BlogCreationRequest;
import com.jochman.components.requestBodies.NotificationRequest;
import com.jochman.components.requestBodies.PostCreationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final PostRepository postRepository;
    private final BloggerRepository bloggerRepository;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void createBlog(BlogCreationRequest blogCreationRequest, Long bloggerId){
        Blog blog = Blog.builder()
                .blogger(bloggerRepository.findById(bloggerId).get())
                .blogName(blogCreationRequest.blogName())
                .blogDescription(blogCreationRequest.blogDescription())
                .build();
        //todo: check if blogName isn't taken
        blogRepository.save(blog);

        NotificationRequest notificationRequest = new NotificationRequest(
                NotificationRequest.EntityType.BLOG,
                blog.getBlogId(),
                String.format("Your %s blog has been created!", blog.getBlogName())
        );

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }

//    public void addPost(PostCreationRequest postCreationRequest, Long blogId){
//        Blog blog = blogRepository.findById(blogId).get();
//        //todo:make this method talk to Post service to receive a post
//        Post post = Post.builder()
//                .blog(blog)
//                .postTitle(postCreationRequest.postTitle())
//                .postContent(postCreationRequest.postContent())
//                .build();
//
//        //todo:check if post's title isn't taken
//        blog.addPost(post);
//        postRepository.saveAndFlush(post);
//
//        NotificationRequest notificationRequest = new NotificationRequest(
//                NotificationRequest.EntityType.POST,
//                post.getPostId(),
//                String.format("Your %s post has been created!", post.getPostTitle())
//        );
//
//        rabbitMQMessageProducer.publish(
//                notificationRequest,
//                "internal.exchange",
//                "internal.notification.routing-key"
//        );
//    }

    public Blog getBlog(Long blogId) {
        return blogRepository.findById(blogId).get();
    }

    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }
}
