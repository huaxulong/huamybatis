package com.huaxu.minimybatis.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-03 2:55 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class TestAQS {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);

        try {
            lock.lock();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                }
            }, "thread-01");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                }
            });

            int cnt = 0;
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                cnt = cnt + i;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}
