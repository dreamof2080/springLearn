package com.jeffrey.learn.base.singleton;

/**
 * @author: jeffrey
 * @date: 1/13/20 10:40 AM
 */
public class TestUser {
    public static void main(String[] args) {
        User instance = User.getInstance();
        System.out.println(User.hobbies);
        System.out.println(User.age);
    }
}
