package com.jochman.blogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "com.jochman.blogger",
                "com.jochman.amqp",
                "com.jochman.components.exception"
        }
)
@EnableEurekaClient
@EntityScan("com.jochman.components.entities")
@EnableJpaRepositories("com.jochman.components.repositories")
@EnableFeignClients(
        basePackages = "com.jochman.components.clients"
)
public class BloggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BloggerApplication.class, args);
    }
}
