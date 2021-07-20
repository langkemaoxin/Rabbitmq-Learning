package com.lagou.edu;

import java.util.concurrent.BlockingQueue;

/**
 *
 *  创建一个生产者
 */
public class Producer implements Runnable {

    /**
     * 使用阻塞队列
     */
    private BlockingQueue<Mask> queue;

    /**
     * 构造函数中传入阻塞队列
     * @param queue
     */
    public Producer(BlockingQueue<Mask> queue) {
        this.queue = queue;
    }

    private Integer index=0;

    public void run() {

        while (true){
            try {
                Thread.sleep(100);

                //不停的往队列里面添加数据

                //当队列中所剩的空间满了，没有多余的空间给队列存放了
                if(queue.remainingCapacity()<=0){
                    System.out.println("堆积如山了");
                }else{
                    Mask mask = new Mask(1,"红蜻蜓");
                    System.out.println("正在生产第"+(index++)+"个口罩");
                    queue.put(mask);
                    System.out.println("已经生产的口罩数量："+queue.size());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
























