package com.lagou.edu;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<Mask> queue;

    public Consumer(BlockingQueue<Mask> queue) {
        this.queue = queue;
    }

    public void run() {

        while (true){
            try {
                Thread.sleep(1000);

                System.out.println("正在买口罩...");
                Mask mask = queue.take();

                System.out.println("买到了"+mask.toString());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
