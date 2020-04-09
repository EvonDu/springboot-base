package com.self.base.services.amqp.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

/**
 * 消息队列消费者服务
 * 监听队列:ExampleQueue
 * 服务开启:把注释@Component和@RabbitListener去掉
 */
//@Component
//@RabbitListener(queues="ExampleQueue")
public class AmqpListener {

    /**
     * 消息格式为String时的处理方法
     * @param message String消息
     */
    @RabbitHandler
    public void onMessage(String message) {
        System.out.println("消费者收到消息: " + message);
    }

    /**
     * 消息格式为JSON时的处理方法
     * @param data JSON对象
     */
    /*@RabbitHandler
    public void onMessage(LinkedHashMap data) {
        System.out.println("消费者收到消息: " + data.get("key"));
    }*/

}