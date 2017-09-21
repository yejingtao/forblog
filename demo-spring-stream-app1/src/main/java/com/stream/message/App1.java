package com.stream.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

import com.stream.entity.Device;

@EnableBinding(Processor.class)
public class App1 {

	private static Logger logger = LoggerFactory.getLogger(App1.class);
	
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public String receiveMethod(Device device) {
		logger.info("App1 ： " + device);
		return "Hello App2";
	}
}
