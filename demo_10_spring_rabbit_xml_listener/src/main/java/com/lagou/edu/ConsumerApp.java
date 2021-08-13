package com.lagou.edu;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerApp {
    public static void main(String[] args) {

        //直接加载xml文件，然后监听器容器就会运行
        new ClassPathXmlApplicationContext("spring-rabbit.xml");
    }
}
