package com.huaxu.minimybatis.juc.lock.reentranlock;

/**
 * @description: StoreInterface
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-06 7:19 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface StoreInterface {

    void produce(int number);
    void consume(int number);

}
