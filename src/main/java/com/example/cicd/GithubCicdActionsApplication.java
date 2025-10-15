package com.example.cicd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@RestController
public class GithubCicdActionsApplication {

	public static Logger logger = LoggerFactory.getLogger(GithubCicdActionsApplication.class);

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to my application!";
	}

	@PostConstruct
	public void init() {
		logger.info("Application stated...");
	}
	
	// update

	public static void main(String[] args) {
		logger.info("Application executed...");
		SpringApplication.run(GithubCicdActionsApplication.class, args);
	}

}
