package com.yomuka.rsv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.yomuka.*")
public class YomukaRsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(YomukaRsvApplication.class, args);
	}

}

