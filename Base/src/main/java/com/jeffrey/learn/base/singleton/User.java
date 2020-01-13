package com.jeffrey.learn.base.singleton;

/**
 * @author: jeffrey
 * @date: 1/13/20 10:38 AM
 */
public class User {

    public static int hobbies;
    public static int age = 3;
    private static User user = new User();

    private User() {
        System.out.println("构造方法");
        hobbies++;
        age++;
    }

    public static User getInstance() {
        System.out.println("getInstance");
        return user;
    }
}
