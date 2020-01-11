package com.jeffrey.learn.async.listener;

import com.jeffrey.learn.async.event.NoticeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author: jeffrey
 * @date: 1/11/20 12:39 PM
 */
@Component
public class NoticeListener implements ApplicationListener<NoticeEvent> {

    private static final Logger logger = LoggerFactory.getLogger(NoticeListener.class);

    @Async
    @Override
    public void onApplicationEvent(NoticeEvent event) {
        logger.info("listener get event, sleep 2 seconds...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("event message is : {}", event.getMessage());
    }
}
