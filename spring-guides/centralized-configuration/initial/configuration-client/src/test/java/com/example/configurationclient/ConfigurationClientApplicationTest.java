package com.example.configurationclient;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootTest
class ConfigurationClientApplicationTest {

	@Autowired
	private ConfigurableEnvironment environment;

	@Autowired
	private MessageRestController controller;

	@Autowired
	private ContextRefresher refresher;


	@Test
	void contextLoads() {
		assertThat(controller.getMessage()).isNotEqualTo("Hello test");
		TestPropertyValues.of("message:Hello test").applyTo(environment);

		assertThat(controller.getMessage()).isNotEqualTo("Hello test");

		refresher.refresh();
		assertThat(controller.getMessage()).isEqualTo("Hello test");
	}

}
