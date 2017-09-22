package com.yejingtao.trace2.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraceController {
	
	private final Logger logger = LoggerFactory.getLogger(TraceController.class);
	
	@RequestMapping(value="/trace2", method=RequestMethod.GET)
	public String trace() {
		logger.info("======<call trace 2>=======");
		return "trance-2";
	}
}
