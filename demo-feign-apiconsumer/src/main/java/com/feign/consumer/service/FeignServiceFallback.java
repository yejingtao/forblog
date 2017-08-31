package com.feign.consumer.service;

import org.springframework.stereotype.Component;
import com.yejingtao.feign.entity.UserDemo;

@Component
public class FeignServiceFallback implements FeignService{

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
