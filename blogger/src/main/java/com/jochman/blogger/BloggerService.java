package com.jochman.blogger;

import com.jochman.amqp.RabbitMQMessageProducer;
import com.jochman.components.repositories.BlogRepository;
import com.jochman.components.requestBodies.BlogCreationRequest;
import com.jochman.components.entities.Blog;
import com.jochman.components.entities.Blogger;
import com.jochman.components.repositories.BloggerRepository;
import com.jochman.components.requestBodies.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class BloggerService {

    private final BloggerRepository bloggerRepository;
    private final BlogRepository blogRepository;
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

    public void addBlog(BlogCreationRequest blogCreationRequest, Long bloggerId){
        Blogger blogger = bloggerRepository.findById(bloggerId).get();
        //todo:make this method talk to Blog service to receive a blog
        Blog blog = Blog.builder()
                .blogger(blogger)
                .blogName(blogCreationRequest.blogName())
                .blogDescription(blogCreationRequest.blogDescription())
                .build();
        blogger.addBlog(blog);
        blogRepository.saveAndFlush(blog);

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

    public Blogger getBlogger(Long bloggerId) {
        return bloggerRepository.findById(bloggerId).get();
    }

    public Set<Blog> getBlogs(Long bloggerId) {
        Blogger blogger = bloggerRepository.findById(bloggerId).get();
        return blogger.getBlogSet();
    }

    public List<Blogger> getAllBloggers() {
        return bloggerRepository.findAll();
    }
}
