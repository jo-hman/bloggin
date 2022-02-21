package com.jochman.post;

import com.jochman.amqp.RabbitMQMessageProducer;
import com.jochman.components.entities.Blogger;
import com.jochman.components.entities.Post;
import com.jochman.components.repositories.BlogRepository;
import com.jochman.components.repositories.PostRepository;
import com.jochman.components.requestBodies.NotificationRequest;
import com.jochman.components.requestBodies.PostCreationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final BlogRepository blogRepository;

    public Post getPost(Long postId) {
        return postRepository.findById(postId).get();
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void createPost(PostCreationRequest postCreationRequest, Long blogId) {
        Post post = Post.builder()
                .blog(blogRepository.findById(blogId).get())
                .postTitle(postCreationRequest.postTitle())
                .postContent(postCreationRequest.postContent())
                .build();

        //todo:check if post's title isn't taken
        postRepository.saveAndFlush(post);

        NotificationRequest notificationRequest = new NotificationRequest(
                NotificationRequest.EntityType.POST,
                post.getPostId(),
                String.format("Your %s post has been created!", post.getPostTitle())
        );

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }
}
