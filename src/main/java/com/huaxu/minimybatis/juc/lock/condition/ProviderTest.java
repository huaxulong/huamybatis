package com.huaxu.minimybatis.juc.lock.condition;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-04-06 9:51 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class ProviderTest {


    public static void main(String[] args) throws InterruptedException {
        Provider provider = new Provider();
        provider.run();

        Consumer consumer = new Consumer();
        consumer.run();

        Thread.sleep(100000);
    }

}
