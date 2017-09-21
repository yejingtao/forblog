package com.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class SenderController {

	@Autowired
	@Qualifier("myinput")
	private MessageChannel myinput;
	
	
	
	@RequestMapping("/mySend")
	public String sendMessage() {
		myinput.send(MessageBuilder.withPayload("my self channel").build());
		return "OK";
	}
}
