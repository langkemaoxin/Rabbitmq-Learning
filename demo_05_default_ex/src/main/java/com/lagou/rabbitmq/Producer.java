package com.lagou.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //首先声明一个队列,防止队列不存在
        channel.queueDeclare("queue.default.ex",false,false,false,null);

        // 发送消息的时候，一般的做法是发送到某个指定的 交换器上，然后再指定 路由键
        // 这里使用的是默认的交换机，没用名字，那么指定的路由键就是队列名称
        // 如果我们显式指定了交换机，那么必须将交换机，队列，绑定键进行绑定
        channel.basicPublish("","queue.default.ex",false,null,"hello lgou".getBytes());

//        这是一般的写法，必须进行绑定映射
//        channel.queueDeclare("queue.biz",false,false,false,null);
//        channel.exchangeDeclare("exchange.biz", BuiltinExchangeType.DIRECT,false,false,null);
//        //把交换机和队列进行绑定映射
//        channel.queueBind("queue.biz","exchange.biz","hello.world");

        channel.close();
        connection.close();
    }
}
