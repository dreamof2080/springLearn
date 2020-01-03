package com.jeffrey.learn;

import com.jeffrey.learn.event.MyEvent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.jeffrey.learn.publisher.MyPublisher;

import javax.annotation.Resource;

@SpringBootTest
class LearnApplicationTests {

	@Resource
	private MyPublisher myPublisher;

	@Test
	void contextLoads() {
		MyEvent event = new MyEvent("我的事件");
		myPublisher.publishEvent(event);
	}

}
