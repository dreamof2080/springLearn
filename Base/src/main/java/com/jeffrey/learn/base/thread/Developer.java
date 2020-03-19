package com.jeffrey.learn.base.thread;

/** 开发人员
 * @author jeffrey
 * @date 3/19/20 8:41 AM
 */
public class Developer implements Runnable{
    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 0; i < 1000000; i++) {

                }
                System.out.println("张三:开发中，请勿打扰....");
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("张三: 参加会议，退出开发");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
