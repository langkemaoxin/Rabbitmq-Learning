package com.lagou.edu.demo_14_springboot_rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 相比于Spring， SpringBoot 把相关的配置参数，放入到了Properties
 * 然后少了Template,Admin的初始化
 *
 *
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue("queue.boot", false, false, false, null);
    }

    @Bean
    public Exchange exchange(){
        return new TopicExchange("ex.boot",false,false,null);
    }

    /**
     * 创建一个Binding对象,
     * 都是指定队列名称
     * Binding.DestinationType.QUEUE
     * @return
     */
    @Bean
    public Binding binding(){
        return new Binding("queue.boot"
                ,Binding.DestinationType.QUEUE
                ,"ex.boot"
                ,"key.boot"
                ,null);
    }


}
