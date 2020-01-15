package com.jeffrey.learn.base.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jeffrey
 * @date 1/15/20 9:53 AM
 */
@Component
public class TestUseService1 {

    private static final Logger logger = LoggerFactory.getLogger(TestUseService1.class);

    @Resource
    private TestService testService;

    public void test() {
        logger.info("执行加法前count: {}", testService.getCount());
        testService.plus(10);
        logger.info("执行加法后count: {}", testService.getCount());
        try {
            Thread.sleep(3000);
            logger.info("睡眠3秒后count: {}", testService.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
