package com.jochman.blogger;

import com.jochman.amqp.RabbitMQMessageProducer;
import com.jochman.components.clients.blog.BlogClient;
import com.jochman.components.clients.post.PostClient;
import com.jochman.components.repositories.BlogRepository;
import com.jochman.components.requestBodies.BlogCreationRequest;
import com.jochman.components.entities.Blog;
import com.jochman.components.entities.Blogger;
import com.jochman.components.repositories.BloggerRepository;
import com.jochman.components.requestBodies.NotificationRequest;
import com.jochman.components.requestBodies.PostCreationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
@AllArgsConstructor
public class BloggerService {

    private final BloggerRepository bloggerRepository;
    private final BlogRepository blogRepository;
    private final PostClient postClient;
    private final BlogClient blogClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerBlogger(BloggerRegistrationRequest bloggerRegistrationRequest){
        Blogger blogger = Blogger.builder()
                .nickName(bloggerRegistrationRequest.nickName())
                .email(bloggerRegistrationRequest.email())
                .build();
        //todo: check if nickname isn't taken
        //todo: check if email is valid
        bloggerRepository.save(blogger);

        NotificationRequest notificationRequest = new NotificationRequest(
                NotificationRequest.EntityType.BLOGGER,
                blogger.getBloggerId(),
                String.format("Welcome %s to our blogging website!", blogger.getNickName())
        );

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }

    public ResponseEntity addBlog(BlogCreationRequest blogCreationRequest, Long bloggerId){
        Blogger blogger = bloggerRepository.findById(bloggerId).get(); //checks if bloggerId exists
        return blogClient.createBlog(blogCreationRequest, bloggerId);
        //TODO blogger verification

//        Blog blog = Blog.builder()
//                .blogger(blogger)
//                .blogName(blogCreationRequest.blogName())
//                .blogDescription(blogCreationRequest.blogDescription())
//                .build();
//        blogger.addBlog(blog);
//        blogRepository.saveAndFlush(blog);
//
//        NotificationRequest notificationRequest = new NotificationRequest(
//                NotificationRequest.EntityType.BLOG,
//                blog.getBlogId(),
//                String.format("Your %s blog has been created!", blog.getBlogName())
//        );
//
//        rabbitMQMessageProducer.publish(
//                notificationRequest,
//                "internal.exchange",
//                "internal.notification.routing-key"
//        );
    }

    public Blogger getBlogger(Long bloggerId){
        return bloggerRepository.findById(bloggerId).orElseThrow();
    }

    public Set<Blog> getBlogs(Long bloggerId) {
        Blogger blogger = bloggerRepository.findById(bloggerId).get();
        return blogger.getBlogSet();
    }

    public List<Blogger> getAllBloggers() {
        return bloggerRepository.findAll();
    }

    public void deleteBlogger(Long bloggerId) {
        bloggerRepository.deleteById(bloggerId);
    }

    public ResponseEntity createPost(PostCreationRequest postCreationRequest, Long bloggerId, Long blogId) throws NoSuchElementException {
        Blogger blogger = bloggerRepository.findById(bloggerId).get();
        Blog blog = blogRepository.findById(blogId).get();
        //TODO blogger and blog verification

        return postClient.createPost(postCreationRequest, blogId);
    }
}
