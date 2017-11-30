package com.sso.client.plugin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PlugConfiguration {
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
