package com.huaxu.minimybatis.juc.lock.reentranlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-06 8:01 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class MyThreadTest {

    private static final Lock lock = new ReentrantLock(true);
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void add() throws InterruptedException {
        try {lock.lock();//申请获得锁
            System.out.println(Thread.currentThread().getName() + " 线程获得锁～");
            //一般为了安全,一般用try把同步代码块的内容包裹起来
            //保证即使当前线程运行过程中出现异常,也能正常释放锁
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        } finally {
            lock.unlock();
            //一般在finally里释放锁
            System.out.println(Thread.currentThread().getName() + " 线程释放锁～");
        }
    }

    public static void main(String[] args) {
        MyThreadTest m = new MyThreadTest();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //新建10个线程,加入threads中
            Thread thread = new Thread(() -> {
                try {
                    m.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "thread-" + i);
            threads.add(thread);
        }
        //逐一启动线程
        threads.forEach((thread -> thread.start()));
        //在主线程中利用join方法,等待所有方法完成
        /*threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //所有子线程运行结束,打印count值
        System.out.println(m.getCount());
    }

}
