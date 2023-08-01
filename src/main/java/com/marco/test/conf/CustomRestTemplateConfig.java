package com.marco.test.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomRestTemplateConfig {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
