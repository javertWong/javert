package com.wjk.dubbo.swimlane.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class JavertDubboSwimlaneProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavertDubboSwimlaneProviderApplication.class, args);
    }

}
