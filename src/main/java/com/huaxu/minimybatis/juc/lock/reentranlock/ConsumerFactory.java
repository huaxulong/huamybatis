package com.huaxu.minimybatis.juc.lock.reentranlock;

/**
 * @description: ConsumerFactory
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-06 7:18 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class ConsumerFactory extends Thread{

    private int number;
    private StoreInterface  storeHouse;

    public ConsumerFactory(int number,StoreInterface storeHouse){
        this.number =number;
        this.storeHouse = storeHouse;
    }

    public void  consume(int number){
        storeHouse.consume(number);
    }

    @Override
    public void run() {
        consume(number);
    }

}
