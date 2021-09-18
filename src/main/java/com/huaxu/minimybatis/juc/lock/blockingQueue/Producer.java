package com.huaxu.minimybatis.juc.lock.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-06 9:04 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Producer extends Thread{

    ArrayBlockingQueue<String> abq = null;

    public Producer(ArrayBlockingQueue<String> abq) {
        this.abq = abq;
    }

    @Override
    public void run() {
        int i = 0;
        while(true) {
            try {
                Thread.sleep(1000);
                abq.put(""+i);
                System.out.println("存放数据：===="+i+"\t剩余数据量："+abq.size());
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
