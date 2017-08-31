package com.yejingtao.feign.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yejingtao.feign.entity.UserDemo;

public interface HelloService {
	
	@RequestMapping(value="/feign-service/serviceGet",method=RequestMethod.GET)
	String helloService(@RequestParam("name") String name);
	
	@RequestMapping(value="/feign-service/serviceHead", method=RequestMethod.HEAD)
	String helloService(@RequestHeader("name") String name,
			@RequestHeader("password") String password);
	
	@RequestMapping(value="/feign-service/servicePost", method=RequestMethod.POST)
	String helloService(@RequestBody UserDemo userDemo);
	
	

}
