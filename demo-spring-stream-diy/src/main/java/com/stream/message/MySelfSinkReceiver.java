package com.stream.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import com.stream.diy.MySelfSink;


@EnableBinding(MySelfSink.class)
public class MySelfSinkReceiver {
	
	private static Logger logger = LoggerFactory.getLogger(MySelfSinkReceiver.class);
	
	@StreamListener(MySelfSink.INPUT)
	public void receiveMethod(String message) {
		logger.info("receiveMethod ： " + message);
	}
	
}
