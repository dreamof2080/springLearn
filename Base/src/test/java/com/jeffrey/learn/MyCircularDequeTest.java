package com.jeffrey.learn;

import com.jeffrey.learn.base.collection.MyCircularDeque;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * deque测试
 *
 * @author Jeffrey.Liu
 * @date 2020-03-01 15:42
 **/
public class MyCircularDequeTest {
    private final static Logger log = LoggerFactory.getLogger(MyCircularDequeTest.class);

    /**
     *  * 示例1：
     *  * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
     *  * circularDeque.insertLast(1);       // 返回 true
     *  * circularDeque.insertLast(2);       // 返回 true
     *  * circularDeque.insertFront(3);     // 返回 true
     *  * circularDeque.insertFront(4);    // 已经满了，返回 false
     *  * circularDeque.getRear();     // 返回 2
     *  * circularDeque.isFull();         // 返回 true
     *  * circularDeque.deleteLast();  // 返回 true
     *  * circularDeque.insertFront(4);   // 返回 true
     *  * circularDeque.getFront();      // 返回 4
     */
    @Test
    public void Test1() {
        MyCircularDeque deque = new MyCircularDeque(3);
        log.info(deque.insertLast(1) ? "true" : "false");
        log.info(deque.insertLast(2) ? "true" : "false");
        log.info(deque.insertFront(3) ? "true" : "false");
        log.info(deque.insertFront(4) ? "true" : "false");
        log.info(deque.getRear().toString());
        log.info(deque.isFull() ? "true" : "false");
        log.info(deque.deleteLast() ? "true" : "false");
        log.info(deque.insertFront(4) ? "true" : "false");
        log.info(deque.getFront().toString());
    }

    /**
     *  * 示例2：
     *  * MyCircularDeque circularDeque = new MycircularDeque(5); // 设置容量大小为5
     *  circularDeque.insertFront(10); // true
     *  circularDeque.insertLast(4); // true
     *  circularDeque.insertFront(3); // true
     *  circularDeque.insertLast(2); // true
     *  circularDeque.getRear(); // 2
     *  circularDeque.getFront(); // 3
     *  circularDeque.deleteFront(); // true
     *  circularDeque.getFront(); // 10
     *  circularDeque.getRear(); // 2
     */
    @Test
    public void Test2() {
        MyCircularDeque deque = new MyCircularDeque(5);
        log.info(deque.insertFront(10) ? "true" : "false");
        log.info(deque.insertLast(4) ? "true" : "false");
        log.info(deque.insertFront(3) ? "true" : "false");
        log.info(deque.insertLast(2) ? "true" : "false");
        log.info(deque.getRear().toString());
        log.info(deque.getFront().toString());
        log.info(deque.deleteFront() ? "true" : "false");
        log.info(deque.getFront().toString());
        log.info(deque.getRear().toString());
    }
}
