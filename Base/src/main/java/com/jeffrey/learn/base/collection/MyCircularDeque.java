package com.jeffrey.learn.base.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2. 设计实现双端队列，不要使用内置的双端队列库。
 * 你的实现需要支持以下操作:
 * MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * isEmpty()：检查双端队列是否为空。
 * isFull()：检查双端队列是否满了。
 * 示例：
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1);       // 返回 true
 * circularDeque.insertLast(2);       // 返回 true
 * circularDeque.insertFront(3);     // 返回 true
 * circularDeque.insertFront(4);    // 已经满了，返回 false
 * circularDeque.getRear();     // 返回 2
 * circularDeque.isFull();         // 返回 true
 * circularDeque.deleteLast();  // 返回 true
 * circularDeque.insertFront(4);   // 返回 true
 * circularDeque.getFront();      // 返回 4
 *
 * @author Jeffrey.Liu
 * @date 2020-03-01 14:28
 **/
public class MyCircularDeque {
    private final static Logger log = LoggerFactory.getLogger(MyCircularDeque.class);
    // 用数组存储队列元素
    private Object[] items;
    // 队列头部索引
    private int head;
    // 队列尾部索引
    private int tail;

    // 构造方法
    public MyCircularDeque(int k) {
        items = new Object[k];
        head = 0;
        tail = k - 1;
    }

    // 将一个元素添加到双端队列头部。 如果操作成功返回 true
    public boolean insertFront(Object e) {
        if (isFull()) {
            return false;
        }
        // 把头部元素向右移动一位，把items[0]腾出来
        for (int i = head; i > 0; i--) {
            items[i] = items[i - 1];
        }
        // 给0索引对应的元素赋值
        items[0] = e;
        // 头部索引向后移一位
        head++;
        return true;
    }

    // 将一个元素添加到双端队列尾部。如果操作成功返回 true。
    public boolean insertLast(Object e) {
        if (isFull()) {
            return false;
        }
        // 把尾部元素向前移动一位，把items[items.length]腾出来
        for (int i = tail; i < items.length - 1; i++) {
            items[i] = items[i + 1];
        }
        // 给最后一个索引对应的元素赋值
        items[items.length - 1] = e;
        // 尾部索引向前移一位
        tail--;
        return true;
    }

    // 从双端队列头部删除一个元素。 如果操作成功返回 true。
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        // 把头部元素向前移动一位
        for (int i = 0; i < head - 1; i++) {
            items[i] = items[i + 1];
        }
        head--;
        return true;
    }

    // 从双端队列尾部删除一个元素。如果操作成功返回 true。
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        for (int i = items.length - 1; i > tail + 1 ; i--) {
            items[i] = items[i - 1];
        }
        tail++;
        return true;
    }

    // 从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
    public Object getFront() {
        if (isEmpty()) {
            return -1;
        }
        return items[0];
    }

    // 获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
    public Object getRear() {
        if (isEmpty()) {
            return -1;
        }
        return items[items.length - 1];
    }

    // 检查双端队列是否满了
    public boolean isFull() {
        // 头部元素个数
        int h_nums = head;
        // 尾部元素个数
        int t_nums = items.length - tail - 1;
        return items.length == h_nums + t_nums;
    }

    // 检查双端队列是否为空
    public boolean isEmpty() {
        return head == 0 && tail == items.length - 1;
    }
}
