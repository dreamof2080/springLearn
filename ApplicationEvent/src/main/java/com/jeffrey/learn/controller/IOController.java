package com.jeffrey.learn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jeffrey
 * @date: 1/11/20 3:44 PM
 *
 * IO测试
 */
@RestController
@RequestMapping("/io")
public class IOController {

    private static final Logger logger = LoggerFactory.getLogger(IOController.class);

    @GetMapping("/test1")
    public void test () {
        logger.info("执行方法： {}", "test1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("执行方法： {} 完成", "test1");
    }

    @GetMapping("/test2")
    public void tes2 () {
        logger.info("执行方法： {}", "test2");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("执行方法： {} 完成", "test2");
    }
}
