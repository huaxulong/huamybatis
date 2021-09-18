package com.huaxu.minimybatis.juc.lock.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-06 9:04 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Consumer extends Thread{

    ArrayBlockingQueue<String> abq = null;

    public Consumer(ArrayBlockingQueue<String> abq) {
        super();
        this.abq = abq;
    }

    @Override
    public void run() {
        while(true) {
            try{
                Thread.sleep(500);
                String msg = abq.remove();
                System.out.println("取数据：===="+msg+"\t剩余数据量："+abq.size());
            } catch (Exception e) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
