package com.yejingtao.trace1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient

public class Trace1Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Trace1Application.class, args);
	}
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	/**
	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}
	*/
	
}
