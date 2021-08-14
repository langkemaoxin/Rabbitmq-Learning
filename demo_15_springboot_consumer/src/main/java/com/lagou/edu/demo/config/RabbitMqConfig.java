package com.lagou.edu.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    /**
     * 作为消费者而言，消费者，只需要声明定义队列即可，
     * 然后从队列中获取数据
     *
     * @return
     */
    @Bean
    public Queue queue(){
        return QueueBuilder.nonDurable("queue.boot").build();
    }

}
