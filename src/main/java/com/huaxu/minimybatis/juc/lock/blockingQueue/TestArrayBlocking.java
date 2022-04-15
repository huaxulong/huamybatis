package com.huaxu.minimybatis.juc.lock.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-06 9:03 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class TestArrayBlocking {

    public static void main(String[] args) throws Exception {
//		insertBlocking();
		fetchBlocking();

//        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<String>(10);
//        testProducerConsumer(abq);

    }


    /**
     * 此方法展示了 ArrayBlockingQueue 的插入阻塞特性 ：如果队列已经满了，那么插入的操作就会被阻塞，程序执行就会被迫暂停。
     */
    public static void insertBlocking() throws InterruptedException {
        ArrayBlockingQueue<String> names = new ArrayBlockingQueue<String>(1, true);
        System.out.println("put a");
        names.put("a");
        // 从这一句开始后面的就不会被执行了
        System.out.println(names.take());
        names.put("b");
        names.put("c");

        System.out.println("程序执行到此...");
    }

    /**
     * 此方法展示了 ArrayBlockingQueue 的取出阻塞特性 ：如果队列为空，那么取的操作就会被阻塞，程序执行就会报错。
     *
     */
    public static void fetchBlocking() throws InterruptedException {
        ArrayBlockingQueue<String> names = new ArrayBlockingQueue<String>(1);
        names.put("a");
//        names.remove();
//        names.remove();
        names.put("b");

        System.out.println("程序执行到此...");
    }


    /**
     * @作用 此方法用来测试生产者和消费者
     * 为了让程序在获取不到元素时不报错有两种方式：
     * 1.让生产者的生产速度大于消费者的消费速度
     * 2.在消费者获取资源出错时让消费者线程暂停一段时间，不输出错误。
     * @param abq
     */
    public static void testProducerConsumer (ArrayBlockingQueue<String> abq) {
        Thread tConsumer = new Consumer(abq);
        Thread tProducer = new Producer(abq);
        tConsumer.start();
        tProducer.start();
    }

}
