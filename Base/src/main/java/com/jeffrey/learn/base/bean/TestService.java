package com.jeffrey.learn.base.bean;

import org.springframework.stereotype.Component;

/**
 * @author jeffrey
 * @date 1/15/20 9:50 AM
 */
@Component
public class TestService {

    private int count;

    public int getCount() {
        return count;
    }

    public void plus(int num) {
        this.count += num;
    }

    public void minus(int num) {
        this.count -= num;
    }
}
