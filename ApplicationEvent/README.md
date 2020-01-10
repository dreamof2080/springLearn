# Spring事件驱动模型

## 使用场景
比如说订单状态变化的时候，能够通知到邮件服务，短信服务，积分变化等；如果你是各新手，想象一下你去如何实现这个业务的代码？
肯定是一个OrderService里面引入积分Service，短信Service，邮件Service，还有很多很多Service，可能还要调用第三方接口。
时不时发现问题所在了，Service耦合严重，还会出现循环引用的问题，代码超长，以至于不方便维护。

## 核心概念
* 事件： ApplicationEvent
* 监听者： ApplicationListener
* 事件发布： ApplicationContext.publishEvent(ApplicationEvent event)

## 常用事件驱动模型
* 设计模式里面的观察者模式
* JDK观察者模式
* JavaBean事件驱动
* Spring事件驱动