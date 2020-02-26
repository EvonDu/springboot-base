package com.self.base.services.amqp.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置类
 * 配置类配置:队列、交换机、路由键
 * 至于AMQP的服务器(host、port、username、password)配置在application.yml中配置
 * 服务开启:把注释@Configuration去掉
 */
//@Configuration
public class AmqpConfig {

    /**
     * 相关参数
     */
    private String  queue1      = "ExampleQueue";
    private String  exchange    = "ExampleExchange";
    private String  routingKey  = "topic.*";

    /**
     * 定义队列
     * @return Queue
     */
    @Bean
    public Queue getQueue() {
        return new Queue(this.queue1, true);  //true 是否持久
    }

    /**
     * 定义交换机
     * @return TopicExchange
     */
    @Bean
    TopicExchange getExchange() {
        return new TopicExchange(this.exchange);
    }

    /**
     * 绑定队列和交换机,并设置匹配键
     * @return Binding
     */
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(this.getQueue()).to(this.getExchange()).with(this.routingKey);
    }

}
