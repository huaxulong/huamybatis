package com.huaxu.minimybatis.juc.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-13 6:34 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        Worker worker = new Worker();

        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(20, true);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 10, 20, TimeUnit.MILLISECONDS, blockingQueue);

        executor.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 15; i++) {

            executor.execute(worker);

        }


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程开始执行～");
        executor.execute(worker);

        //executor.shutdown();

        //executor.terminated();


    }

    public static class Worker implements Runnable {

        @Override
        public void run() {
            System.out.println("线程名称: " + Thread.currentThread().getName() + "  开始执行");
            try {
                Thread.sleep(1 * 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
