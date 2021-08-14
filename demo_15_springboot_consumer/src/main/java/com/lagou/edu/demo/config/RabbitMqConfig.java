package com.lagou.edu.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    /**
     * 
     * @return
     */
    @Bean
    public Queue queue(){
        return QueueBuilder.nonDurable("queue.boot").build();
    }

}
