package com.yejingtao.trace2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Trance2Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Trance2Application.class, args);
	}
	
	/**
	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}
	*/
}
