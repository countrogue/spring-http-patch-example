package com.countrogue.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class HttpPatchExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpPatchExampleApplication.class, args);
	}
}
