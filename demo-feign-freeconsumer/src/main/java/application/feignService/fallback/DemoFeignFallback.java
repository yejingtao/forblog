package application.feignService.fallback;


import org.springframework.stereotype.Component;
import application.entity.UserDemo;
import application.feignService.DemoFeignService;

@Component
public class DemoFeignFallback implements DemoFeignService{
	@Override
	public String helloService(String name) {
		return "get error";
	}

	@Override
	public String helloService(String name,String password) {
		return "head error";
	}
	
	@Override
	public String helloService(UserDemo userDemo) {
		return "post error";
	}
}
