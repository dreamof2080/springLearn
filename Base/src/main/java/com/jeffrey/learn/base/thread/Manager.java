package com.jeffrey.learn.base.thread;

/**
 * 经理
 * @author jeffrey
 * @date 3/19/20 8:44 AM
 */
public class Manager {

    public static void main(String[] args) {
        System.out.println("manager：这里有个需求，分配给张三");
        Runnable developer = new Developer();
        Thread thread = new Thread(developer);
        thread.start();
        try {
            Thread.sleep(1000);
            System.out.println("manager：大家开个总结会...");
            thread.interrupt();
            Thread.sleep(1000);
            System.out.println("全体会议中...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
