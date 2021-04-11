package com.huaxu.minimybatis.test;

import com.huaxu.minimybatis.locks.MiniReentrantLock;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-04-11 9:39 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class TestReentrantLock {

    @Test
    public void test() {
        final MiniReentrantLock lock = new MiniReentrantLock();
        for (int i = 0; i < 100; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.lock();
                        System.out.println("Thread Name : " + Thread.currentThread().getName() + "   i = " + finalI);
                    } finally {
                        lock.unlock();
                    }
                }
            }).start();
        }

        try {
            TimeUnit.HOURS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
