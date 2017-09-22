package com.yejingtao.trace1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class Trace1Controller {
	
	private final Logger logger = LoggerFactory.getLogger(Trace1Controller.class);
	
	@Autowired
	private RestTemplate restTemplate; 
	
	@RequestMapping(value="/trace1", method=RequestMethod.GET)
	public String trace1() {
		logger.info("======<call trace 1>=======");
		//return "trace1 resutl :"+restTemplate.getForEntity("http://TRACE-2/trace2", String.class).getBody();
		restTemplate.getForEntity("http://TRACE-2/trace2", String.class);
		return "ok";
	}
}
