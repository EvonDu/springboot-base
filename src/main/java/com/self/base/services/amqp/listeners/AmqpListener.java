package com.self.base.services.amqp.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息队列消费者服务
 * 监听队列:ExampleQueue
 * 服务开启:把注释@Component和@RabbitListener去掉
 */
//@Component
//@RabbitListener(queues="ExampleQueue")
public class AmqpListener {

    @RabbitHandler
    public void onMessage(String message) {
        System.out.println("消费者收到消息: " + message);
    }

}