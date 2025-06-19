package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("model")
@EnableJpaRepositories("com.example.demo.repository")
public class PipBoyWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PipBoyWebApplication.class, args);
	}

}
