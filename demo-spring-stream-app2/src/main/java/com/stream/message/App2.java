package com.stream.message;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import com.stream.entity.Device;

import org.springframework.integration.annotation.Poller;

@EnableBinding(Processor.class)
public class App2 {
	
	private static Logger logger = LoggerFactory.getLogger(App2.class);
	
	private int id;
	
	
	@Bean
	@InboundChannelAdapter(value=Processor.OUTPUT, poller=@Poller(fixedDelay="5000"))
	public MessageSource<Device> sendTimeMessage(){
		return ()-> new GenericMessage<>(new Device(id++,"app2DeviceName","app2DeviceType"));
	}
	
	
	@StreamListener(Processor.INPUT)
	public void receiveMethod(Object message) {
		logger.info("App2 ： " + message);
	}
}
