package com.huaxu.minimybatis.juc.lock.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-04-06 9:52 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Provider implements Runnable{

    public static Lock lock = new ReentrantLock();

    public static Condition condition = lock.newCondition();

    public static List<Integer> goodsList = new ArrayList<>();

    private int a = 0;

    private volatile int maxCount = 10;

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                while (goodsList.size() == maxCount) {
                    System.out.println("生产者先等等。。。");
                    condition.await();
                }
                Thread.sleep(30);
                goodsList.add(++a);
                System.out.println("生产者生产商品：" + a);
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
