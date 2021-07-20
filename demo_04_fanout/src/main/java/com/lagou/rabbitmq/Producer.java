package com.lagou.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Producer {
    public static void main(String[] args) throws Exception, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        //如果是fanout类型的，则不需要声明queue，那么究竟发给哪个队列？是现有的每个都发一次？

        channel.exchangeDeclare("ex.myfan","fanout",true,false,null);

        for (int i = 0; i < 20; i++) {
            channel.basicPublish("ex.myfan","",null,("hello world fan:"+i).getBytes());
        }

        channel.close();

        connection.close();



    }
}






















