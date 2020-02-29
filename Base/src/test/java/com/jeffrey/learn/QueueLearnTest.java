package com.jeffrey.learn;

import com.jeffrey.learn.base.collection.QueueLearn;
import org.junit.jupiter.api.Test;

/**
 * queue测试
 *
 * @author Jeffrey.Liu
 * @date 2020-02-29 14:47
 **/
public class QueueLearnTest {

    @Test
    public void test() {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        QueueLearn.lesson1(nums, k);
    }
}
