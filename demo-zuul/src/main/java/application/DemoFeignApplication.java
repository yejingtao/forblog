package application;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import application.filter.AccessFilter;

@EnableZuulProxy
@SpringCloudApplication
public class DemoFeignApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoFeignApplication.class, args);
	}
	
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}

}
