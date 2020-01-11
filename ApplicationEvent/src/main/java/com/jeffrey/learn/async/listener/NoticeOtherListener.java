package com.jeffrey.learn.async.listener;

import com.jeffrey.learn.async.event.NoticeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author: jeffrey
 * @date: 1/11/20 1:14 PM
 */
@Component
public class NoticeOtherListener {

    private static final Logger logger = LoggerFactory.getLogger(NoticeOtherListener.class);

    @EventListener
    @Order(2)
    @Async
    public void receive2(NoticeEvent event) {
        logger.info("receive2 get event, sleep 2 seconds...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("receive2 event message is : {}", event.getMessage());
    }

    @EventListener
    @Order(1)
    @Async
    public void receive1(NoticeEvent event) {
        logger.info("receive1 get event, sleep 2 seconds...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("receive1 event message is : {}", event.getMessage());
    }
}
