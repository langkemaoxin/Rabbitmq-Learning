package com.lagou.edu.demo.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class MyMessageListener {

    /**
     * 使用Listener RabbitListener
     *
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = "queue.boot")
    public void getMessage(Message message, Channel channel) throws IOException {
        String s = new String(message.getBody(), "utf-8");
        System.out.println(s);

        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, true);
    }
}
