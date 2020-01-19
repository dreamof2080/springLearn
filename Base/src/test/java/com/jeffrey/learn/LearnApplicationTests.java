package com.jeffrey.learn;

import com.jeffrey.learn.base.bean.TestUseService1;
import com.jeffrey.learn.base.bean.TestUseService2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class LearnApplicationTests {

	@Resource
	private TestUseService1 service1;
	@Resource
	private TestUseService2 service2;

	@Test
	void contextLoads() {
	}

	@Test
	void testBean() {
		Thread thread1 = new Thread(() -> {
			service1.test();
		});
		Thread thread2 = new Thread(() -> {
			service2.test();
		});
		thread1.start();
		thread2.start();
	}

}
