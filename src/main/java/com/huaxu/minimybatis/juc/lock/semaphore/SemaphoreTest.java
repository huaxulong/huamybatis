package com.huaxu.minimybatis.juc.lock.semaphore;

import java.util.concurrent.*;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-04-09 11:41 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore sph = new Semaphore(0); // 初始化释放几个线程

        ExecutorService pool = new ThreadPoolExecutor(3, 3, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        pool.execute(new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                print();
                sph.release(2);
            }
        });

        pool.execute(new Thread() {
            @Override
            public void run() {
                try {
                    sph.acquire();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                print();
            }
        });

        pool.execute(new Thread() {
            @Override
            public void run() {
                try {
                    sph.acquire();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                print();
            }
        });
    }


    private static void print() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().toString() + "*" + i);
        }
    }


}
