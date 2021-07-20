package com.lagou.edu;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
    public static void main(String[] args) {

//        //声明一个阻塞队列
//        BlockingQueue<Mask> queue =new ArrayBlockingQueue<Mask>(10);
//
//        new Thread(new Producer(queue)).start();
//
//        new Thread(new Consumer(queue)).start();


//        ArrayList list=new ArrayList<Integer>();
//        for (int i = 0; i < Long.MAX_VALUE; i++) {
//            list.add(i);
//        }

        HashMap map=new HashMap();
        for (int i = 0; i < 30; i++) {
            map.put(i,i);

        }

    }
}
