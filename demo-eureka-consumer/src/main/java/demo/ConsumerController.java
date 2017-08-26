package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
	
	@Autowired
	private HelloService helloService;
	
	
	@RequestMapping(value="/ribbon-consumer", method=RequestMethod.GET)
	public String helloConsumer() {
		return helloService.sayHello();
	}
}
