package com.jeffrey.learn.publisher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: jeffrey
 * @date: 1/3/20 10:40 AM
 *
 * 事件发布者
 */
@Component
public class MyPublisher {

    @Resource
    private ApplicationContext applicationContext;


    public void publishEvent(ApplicationEvent event) {
        System.out.println("发布事件");
        applicationContext.publishEvent(event);
    }
}
