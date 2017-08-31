package com.feign.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.feign.consumer.service.FeignService;

@RestController
public class DemoFeignController {
	
	@Autowired
	private FeignService feignService;
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String demoServiceTest() {
		StringBuffer sb = new StringBuffer();
		sb.append(feignService.helloService("yuanyuan"));
		sb.append("\n");
		sb.append(feignService.helloService("yjt","xixihaha"));
		sb.append("\n");
		sb.append(feignService.helloService(new com.yejingtao.feign.entity.UserDemo("yejingtao","123456")));
		return sb.toString();
		
	}
}
