package com.stream.diy;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySelfSink {
	String INPUT = "myinput";

	@Input(MySelfSink.INPUT)
	SubscribableChannel input();
}
