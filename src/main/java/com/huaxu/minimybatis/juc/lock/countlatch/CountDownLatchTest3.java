package com.huaxu.minimybatis.juc.lock.countlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-18 10:54 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class CountDownLatchTest3 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        Thread t1 = new Thread(()-> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignore) {
            }
            // 休息 5 秒后(模拟线程工作了 5 秒)，调用 countDown()
            latch.countDown();
        }, "t1");

        Thread t2 = new Thread(()-> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ignore) {
            }
            // 休息 10 秒后(模拟线程工作了 10 秒)，调用 countDown()
            latch.countDown();
        }, "t2");

        Thread t3 = new Thread(()-> {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException ignore) {
            }
            // 休息 10 秒后(模拟线程工作了 10 秒)，调用 countDown()
            latch.countDown();
        }, "t3");

        t1.start();
        t2.start();
        t3.start();

        Thread t4 = new Thread(()-> {
            try {
                // 阻塞，等待 state 减为 0
                /*Thread.sleep(50);*/
                latch.await();
                System.out.println("线程 t4 从 await 中返回了");
            } catch (InterruptedException e) {
                System.out.println("线程 t4 await 被中断");
                Thread.currentThread().interrupt();
            }
        }, "t4");

        Thread t5 = new Thread(()-> {
            try {
                // 阻塞，等待 state 减为 0
                Thread.sleep(100);
                latch.await();
                System.out.println("线程 t5 从 await 中返回了");
            } catch (InterruptedException e) {
                System.out.println("线程 t5 await 被中断");
                Thread.currentThread().interrupt();
            }
        }, "t5");

        Thread t6 = new Thread(()-> {
            try {
                Thread.sleep(200);
                // 阻塞，等待 state 减为 0
                latch.await();
                System.out.println("线程 t6 从 await 中返回了");
            } catch (InterruptedException e) {
                System.out.println("线程 t6 await 被中断");
                Thread.currentThread().interrupt();
            }
        }, "t6");

        t4.start();
        t5.start();
        t6.start();
    }

}
