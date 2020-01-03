package com.jeffrey.learn.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author: jeffrey
 * @date: 1/3/20 10:21 AM
 *
 * 事件
 */
public class MyEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyEvent(Object source) {
        super(source);
        System.out.println("my Event");
    }

    public void print() {
        System.out.println("hello spring event[MyEvent]");
    }
}
