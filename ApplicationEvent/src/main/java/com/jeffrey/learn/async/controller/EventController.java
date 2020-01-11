package com.jeffrey.learn.async.controller;

import com.jeffrey.learn.async.event.NoticeEvent;
import com.jeffrey.learn.async.publisher.NoticePublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: jeffrey
 * @date: 1/11/20 12:57 PM
 */
@RestController
@RequestMapping("/event")
public class EventController {

    @Resource
    private NoticePublisher noticePublisher;

    @GetMapping("/notice/{message}")
    public void notice(@PathVariable(name = "message") String message) {
        noticePublisher.publishEvent(new NoticeEvent(message));
    }
}
