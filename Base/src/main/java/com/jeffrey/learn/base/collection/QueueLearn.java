package com.jeffrey.learn.base.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * queue学习
 *
 * @author Jeffrey.Liu
 * @date 2020-02-29 14:23
 *
 **/
public class QueueLearn {
    private static final Logger log = LoggerFactory.getLogger(QueueLearn.class);

    /**
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
     * 你只可以看到在滑动窗口内的 k 个数字，1 ≤ k ≤ 输入数组的大小。
     * 滑动窗口每次只向右移动一位。返回滑动窗口中的最大值。
     *
     * 示例：
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     *
     *   滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     */
    public static void lesson1(int[] nums, int k) {
        if (k < 1 || k > nums.length) {
            log.warn("k必须大于1且小于nums的长度");
            return;
        }
        // 构造PriorityQueue
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        // 滑动窗口的前置索引
        int front = 0;
        // 滑动窗口的后置索引
        int rear = k - 1;
        while (rear < nums.length) {
            // 入队
            for (int i = front; i < rear + 1; i++) {
                queue.offer(nums[i]);
            }
            // 输出队列中最大的值
            log.info(String.valueOf(queue.peek()));
            // 出队
            for (int i = 0; i < k; i++) {
                queue.poll();
            }
            front++;
            rear++;
        }
    }
}
