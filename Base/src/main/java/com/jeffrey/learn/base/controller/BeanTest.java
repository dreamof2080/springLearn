package com.jeffrey.learn.base.controller;

import com.jeffrey.learn.base.bean.TestUseService1;
import com.jeffrey.learn.base.bean.TestUseService2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jeffrey
 * @date 1/15/20 10:07 AM
 */
@RestController
@RequestMapping("/bean")
public class BeanTest {

    @Resource
    private TestUseService1 service1;
    @Resource
    private TestUseService2 service2;

    @GetMapping("/test1")
    public String test1() {
        Thread thread1 = new Thread(() -> {
            service1.test();
        });
        Thread thread2 = new Thread(() -> {
            service2.test();
        });
        thread1.start();
        thread2.start();
        return "1";
    }
}
