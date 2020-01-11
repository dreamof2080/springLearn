package com.jeffrey.learn.sync.listener;

import com.jeffrey.learn.sync.event.MyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: jeffrey
 * @date: 1/3/20 10:29 AM
 *
 * 事件监听者
 */
@Component
public class MyListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("into My Listener");
        event.print();
    }
}
