package com.jeffrey.learn;

import com.jeffrey.learn.async.event.NoticeEvent;
import com.jeffrey.learn.async.publisher.NoticePublisher;
import com.jeffrey.learn.sync.event.MyEvent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.jeffrey.learn.sync.publisher.MyPublisher;

import javax.annotation.Resource;

@SpringBootTest
class LearnApplicationTests {

	@Resource
	private MyPublisher myPublisher;

	@Resource
	private NoticePublisher noticePublisher;

	@Test
	void contextLoads() {
		MyEvent event = new MyEvent("我的事件");
		myPublisher.publishEvent(event);
	}

	@Test
	void asyncTest() {
		noticePublisher.publishEvent(new NoticeEvent("jeffrey test event"));
	}

}
