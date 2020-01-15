package com.jeffrey.learn.base.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jeffrey
 * @date 1/15/20 9:56 AM
 */
@Component
public class TestUseService2 {

    private static final Logger logger = LoggerFactory.getLogger(TestUseService2.class);

    @Resource
    private TestService testService;

    public void test() {
        logger.info("执行减法前count: {}", testService.getCount());
        testService.minus(4);
        logger.info("执行减法后count: {}", testService.getCount());
        try {
            Thread.sleep(1000);
            logger.info("睡眠1秒后count: {}", testService.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
