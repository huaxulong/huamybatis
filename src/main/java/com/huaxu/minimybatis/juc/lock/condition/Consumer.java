package com.huaxu.minimybatis.juc.lock.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-04-06 9:55 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Consumer implements Runnable{

    private int a = 1;

    private volatile int maxCount = 10;

    @Override
    public void run() {
        while (true) {
            Provider.lock.lock();
            try {
                while (Provider.goodsList.isEmpty()) {
                    System.out.println("消费者先等等。。。");
                    Provider.condition.await();
                }
                Thread.sleep(30);
                System.out.println("消费者消费商品：" + Provider.goodsList.remove(0));
                Provider.condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Provider.lock.unlock();
            }
        }
    }
}
