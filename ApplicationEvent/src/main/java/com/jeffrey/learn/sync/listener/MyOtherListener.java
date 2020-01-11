package com.jeffrey.learn.sync.listener;

import com.jeffrey.learn.sync.event.MyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: jeffrey
 * @date: 1/3/20 10:29 AM
 *
 * 事件监听者(使用注解不实现接口)
 */
@Component
public class MyOtherListener {

    @EventListener
    @Order(2)
    public void receive2(MyEvent event) {
        System.out.println("监听2");
        event.print();
    }

    @EventListener(classes = {MyEvent.class})
    @Order(1)
    public void receive1(Object event) {
        System.out.println("监听1");
        MyEvent myEvent = (MyEvent) event;
        myEvent.print();
    }
}
