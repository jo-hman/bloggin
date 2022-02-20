package com.jochman.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "com.jochman.post",
                "com.jochman.amqp",
                "com.jochman.components.exception"
        }
)
@EnableEurekaClient
@EntityScan("com.jochman.components.entities")
@EnableJpaRepositories("com.jochman.components.repositories")
public class PostApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);
    }
}
