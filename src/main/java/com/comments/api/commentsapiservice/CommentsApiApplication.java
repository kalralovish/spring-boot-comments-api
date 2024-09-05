package com.comments.api.commentsapiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.comments.api.commentsapiservice.config.TableConfig;

@SpringBootApplication
@EnableConfigurationProperties(TableConfig.class)
public class CommentsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentsApiApplication.class, args);
    }
}