package com.jochman.blogger;

import com.jochman.amqp.RabbitMQMessageProducer;
import com.jochman.components.clients.blog.BlogClient;
import com.jochman.components.clients.post.PostClient;
import com.jochman.components.entities.Role;
import com.jochman.components.repositories.BlogRepository;
import com.jochman.components.repositories.RoleRepository;
import com.jochman.components.requestBodies.BlogCreationRequest;
import com.jochman.components.entities.Blog;
import com.jochman.components.entities.Blogger;
import com.jochman.components.repositories.BloggerRepository;
import com.jochman.components.requestBodies.NotificationRequest;
import com.jochman.components.requestBodies.PostCreationRequest;
import com.jochman.components.requestBodies.RoleRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    private final RoleRepository roleRepository;

    public void registerBlogger(BloggerRegistrationRequest bloggerRegistrationRequest) throws NoSuchElementException{
        Blogger blogger = Blogger.builder()
                .nickname(bloggerRegistrationRequest.nickname())
                .email(bloggerRegistrationRequest.email())
                .roleSet(new HashSet<Role>())
                .build();

        blogger.getRoleSet().add(roleRepository.findByName(bloggerRegistrationRequest.roleName())); //checks if role is valid and adds it

        //todo: check if nickname isn't taken
        //todo: check if email is valid
        bloggerRepository.save(blogger);

        NotificationRequest notificationRequest = new NotificationRequest(
                NotificationRequest.EntityType.BLOGGER,
                blogger.getBloggerId(),
                String.format("Welcome %s to our blogging website!", blogger.getNickname())
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

    public void addRole(RoleRequestBody roleRequestBody) {
        if(roleRepository.findByName(roleRequestBody.name()) != null){
            throw new IllegalArgumentException(roleRequestBody.name());
        }

        Role role = Role.builder().name(roleRequestBody.name()).build();
        roleRepository.save(role);
    }

    public void addRoleToUser(RoleRequestBody roleRequestBody, Long bloggerId) throws NoSuchElementException{
        Role role = roleRepository.findByName(roleRequestBody.name());
        Blogger blogger = bloggerRepository.findById(bloggerId).get();
        if(role != null){
            blogger.getRoleSet().add(role);
            bloggerRepository.save(blogger);
        }
        else{
            Role newRole = Role.builder().name(roleRequestBody.name()).build();
            roleRepository.save(newRole);
            blogger.getRoleSet().add(newRole);
            bloggerRepository.save(blogger);
        }
    }
}
