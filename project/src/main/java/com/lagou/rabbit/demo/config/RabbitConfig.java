package com.lagou.rabbit.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    /**
     * 用于异步更新库存信息的队列
     *
     * @return
     */
    @Bean
    public Queue bizQueue() {
        return QueueBuilder.durable("q.biz").build();
    }

    //异步更新交换机
    @Bean
    public Exchange bizExchange() {
        return ExchangeBuilder.directExchange("e.biz").durable(true).build();
    }

    //异步更新绑定
    @Bean
    public Binding bizBind() {
        return BindingBuilder.bind(bizQueue()).to(bizExchange()).with("r.biz")
                .noargs();
    }

    /**
     * 延迟队列，用于延迟支付，不是死信队列么？
     * <p>
     * 其实这里的延迟队列q.ttl 就是用来作为延迟的，而不是进行消费的队列
     *
     * @return
     */
    @Bean
    public Queue ttlQueue() {
        return QueueBuilder.durable("q.ttl")
                .ttl(10000) //有效期10S
                .deadLetterRoutingKey("r.dlx") //绑定死信交换机
                .deadLetterExchange("e.dlx") //绑定死信队列
                .maxLength(1024 * 1024) // 最多可以传1K的消息
                .build();
    }

    /**
     * 延迟队列 交换机
     *
     * @return
     */
    @Bean
    public Exchange ttlExchange() {
        return ExchangeBuilder.directExchange("e.ttl").build();
    }


    @Bean
    public Binding ttlBind() {
        return BindingBuilder.bind(ttlQueue()).to(ttlExchange()).with("r.ttl")
                .noargs();
    }


    /**
     * 创建死信队列
     *
     * @return
     */
    @Bean
    public Queue dlxQueue() {
        return QueueBuilder.durable("q.dlx").build();
    }

    @Bean
    public Exchange dlxExchange() {
        return ExchangeBuilder.directExchange("e.dlx").build();
    }

    @Bean
    public Binding dlxBind() {
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with("r.dlx")
                .noargs();
    }

    @Bean
    public RabbitTemplate template(ConnectionFactory factory){
        return new RabbitTemplate(factory);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory factory){
        return new RabbitAdmin(factory);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
