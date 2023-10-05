package com.yomuka.yomuka.ord.controller;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.yomuka.yomuka.repository")
@ComponentScan(basePackages = "com.yomuka.yomuka")
@EntityScan(basePackages = "com.yomuka.yomuka.entity")

public class YomukaApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(YomukaApplication.class, args);
    }
}

