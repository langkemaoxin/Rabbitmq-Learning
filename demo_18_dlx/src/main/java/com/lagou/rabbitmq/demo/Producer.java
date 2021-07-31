package com.lagou.rabbitmq.demo;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

public class Producer {
    public static void main(String[] args) throws Exception{

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        //正常业务的交换器
        channel.exchangeDeclare("ex.biz", BuiltinExchangeType.DIRECT,true);

        //声明死信交换器 DLX
        channel.exchangeDeclare("ex.dlx",BuiltinExchangeType.DIRECT,true);
        //声明队列为死信队列
        channel.queueDeclare("queue.dlx",true,false,false,null);
        //绑定死信队列和死信交换机
        channel.queueBind("queue.dlx","ex.dlx","key.dlx");


        Map<String,Object> argument=new HashMap<>();

        //声明消息的过期时间
        argument.put("x-message-ttl",10000);

        //声明当前队列中的消息，如果已经过期了，那么将要把消息发送到哪个交换机
        argument.put("x-dead-letter-exchange","ex.dlx");

        //声明消息过期后，发送的Key是什么
        argument.put("x-dead-letter-routing-key","key.dlx");

        //声明一个正常的队列，但是声明了队列中的属性，当消息过期后，会进行什么行为
        channel.queueDeclare("queue.biz",true,false,false,argument);

        //绑定正常的交换机和消息队列
        channel.queueBind("queue.biz","ex.biz","key.biz");

        channel.basicPublish("ex.biz","key.biz",null,"order.12349912389".getBytes());

        System.out.println("发起一个订单，等待支付");

    }
}
