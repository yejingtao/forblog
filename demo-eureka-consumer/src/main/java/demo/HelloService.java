package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="hellServiceFallback")
	public String sayHello() {
		return restTemplate.getForEntity("http://HELLO-SERVICE/hi?name=yejingtao", String.class).getBody();
	}
	
	@SuppressWarnings("unused")
	private String hellServiceFallback() {
		return "error";
	}

}
