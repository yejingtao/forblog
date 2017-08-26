package demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceDemoApplication.class, args);
	}
	
	
	@Value("${server.port}")
    String port;
	
	
    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
    }
    
    /**
     * @RequestParam一般对于GET
     * @RequestHeader
     * @RequestBody 一般对于POST
     */
}
