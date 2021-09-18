package com.huaxu.minimybatis.juc.lock.blockingQueue;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-06 9:36 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface BlockingQueue<T> {

    void put(T element) throws InterruptedException;

    T take() throws InterruptedException;

}
