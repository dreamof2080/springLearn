package com.jeffrey.learn.async.publisher;

import com.jeffrey.learn.async.event.NoticeEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: jeffrey
 * @date: 1/11/20 12:43 PM
 */
@Component
public class NoticePublisher {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(NoticeEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
