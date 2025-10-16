package com.example.cicd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GithubCicdActionsApplication {

	// public static Logger logger = LoggerFactory.getLogger(GithubCicdActionsApplication.class);

	// @GetMapping("/welcome")
	// public String welcome() {
	// 	return "Welcome to my application!";
	// }

	// @PostConstruct
	// public void init() {
	// 	logger.info("Application stated...");
	// }
	
	// update

	public static void main(String[] args) {
		SpringApplication.run(GithubCicdActionsApplication.class, args);
	}

}
