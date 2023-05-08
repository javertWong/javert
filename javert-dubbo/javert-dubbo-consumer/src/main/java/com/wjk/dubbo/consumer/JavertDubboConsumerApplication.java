package com.wjk.dubbo.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class JavertDubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavertDubboConsumerApplication.class, args);
    }

}
