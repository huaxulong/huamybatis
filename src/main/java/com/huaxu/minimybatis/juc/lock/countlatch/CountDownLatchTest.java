package com.huaxu.minimybatis.juc.lock.countlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-13 5:42 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
                        //Thread.sleep((long) (Math.random() * 1000));
                        System.out.println("子线程"+Thread.currentThread().getName()+"执行完成");
                        latch.countDown();//当前线程调用此方法，则计数减一
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            service.submit(runnable);
        }

        try {
            System.out.println("主线程"+Thread.currentThread().getName()+"等待子线程执行完成...");
            Thread.sleep((long) (1000));
            latch.await();//阻塞当前线程，直到计数器的值为0
            System.out.println("主线程"+Thread.currentThread().getName()+"开始执行...");

            service.shutdown();

            System.out.println("结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
