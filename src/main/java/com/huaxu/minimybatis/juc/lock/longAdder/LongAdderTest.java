package com.huaxu.minimybatis.juc.lock.longAdder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-15 10:36 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class LongAdderTest {

    private static AtomicInteger a = new AtomicInteger(0);
    private static LongAdder b = new LongAdder();

    public static void main(String[] args) throws Exception {

        int NCPU = Runtime.getRuntime().availableProcessors();

        System.out.println(NCPU);
        test(1, 10000000);

        test(10, 10000000);

        test(20, 10000000);

        test(50, 10000000);
        test(100, 10000000);
    }

    /**
     * 测试LongAdder和Atomic的效率
     *
     * @param threadNum 线程数
     * @param times     执行时间
     */
    public static void test(Integer threadNum, Integer times) throws Exception {
        System.out.println("线程数为：" + threadNum);
        testAtomic(threadNum, times);
        testLongAdder(threadNum, times);
    }


    /**
     * 测试Atomic的效率
     *
     * @param threadNum
     * @param times
     */
    public static void testAtomic(Integer threadNum, Integer times) throws InterruptedException {
        //开始时间
        long start = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {

            new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    a.incrementAndGet();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        //结束时间
        long end = System.currentTimeMillis();

        System.out.println("Atomic 消耗时间：" + (end - start));
    }

    /**
     * 测试LongAdder的效率
     *
     * @param threadNum
     * @param times
     */
    public static void testLongAdder(Integer threadNum, Integer times) throws InterruptedException {
        //开始时间
        long start = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {

            new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    b.increment();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        //结束时间
        long end = System.currentTimeMillis();

        System.out.println("LongAdder 消耗时间：" + (end - start));
    }

}
