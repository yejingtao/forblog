package application.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import application.entity.UserDemo;
import application.feignService.DemoFeignService;

@RestController
public class DemoFeignController {
	
	@Autowired
	private DemoFeignService demoFeignService;
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String demoServiceTest() {
		StringBuffer sb = new StringBuffer();
		sb.append(demoFeignService.helloService("yuanyuan"));
		sb.append("\n");
		sb.append(demoFeignService.helloService("yjt","xixihaha"));
		sb.append("\n");
		sb.append(demoFeignService.helloService(new UserDemo("yejingtao","123456")));
		return sb.toString();
		
	}
}
