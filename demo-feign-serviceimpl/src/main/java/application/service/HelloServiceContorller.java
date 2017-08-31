package application.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.yejingtao.feign.api.HelloService;
import com.yejingtao.feign.entity.UserDemo;


@RestController
public class HelloServiceContorller implements HelloService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private void sleep(String methodName) {
		int sleepMinTime = new Random().nextInt(3000);
		logger.info("helloService "+methodName+" sleepMinTime: "+sleepMinTime);
		try {
			Thread.sleep(sleepMinTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String helloService(@RequestParam("name") String name) {
		sleep("get");
		return "HelloServiceImpl name :"+name;
	}
	
	@Override
	public String helloService(@RequestHeader("name") String name,
			@RequestHeader("password") String password) {
		sleep("header");
		return "helloServiceHead name :"+name +" password:"+password;
	}
	
	@Override
	public String helloService(@RequestBody UserDemo userDemo) {
		sleep("post");
		return userDemo.toString();
	}
	
	
}
