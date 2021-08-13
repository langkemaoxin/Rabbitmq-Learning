package com.lagou.edu;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

import java.io.UnsupportedEncodingException;

/**
 * 声明一个消息监听器
 */
public class MyMessageListener implements ChannelAwareMessageListener {

    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println(new String(message.getBody(), message.getMessageProperties().getContentEncoding()));
    }

    public void onMessage(Message message)   {
        try {
            System.out.println(new String(message.getBody(), message.getMessageProperties().getContentEncoding()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    // System.out.println(new String(message.getBody(), message.getMessageProperties().getContentEncoding()));
}
