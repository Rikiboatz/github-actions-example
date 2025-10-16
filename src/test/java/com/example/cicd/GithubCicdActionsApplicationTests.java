package com.example.cicd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @AutoConfigureMockMvc
// @SpringBootTest
// class GithubCicdActionsApplicationTests {

// 	// @Autowired MockMvc mvc;

// 	// public static Logger logger = LoggerFactory.getLogger(GithubCicdActionsApplication.class);

// 	@Test
// 	void contextLoads() {
// 		// logger.info("Test case executing...");
// 		// assertEquals(true, true);
// 	}

// 	// @Test
// 	// void succcessCaseWelcomeTest() throws Exception {
// 	// 	mvc.perform(get("/welcome"))
// 	// 		.andExpect(status().isOk())
// 	// 		.andExpect(result -> result.equals("Welcome to my application!"));
// 	// }

// }


@SpringBootTest
class GithubCicdActionsApplicationTests {

	@Test
	void contextLoads() {
	}

}
