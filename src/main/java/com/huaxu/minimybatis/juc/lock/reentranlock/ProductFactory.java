package com.huaxu.minimybatis.juc.lock.reentranlock;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-06 7:17 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class ProductFactory extends Thread{

    private int number;
    private StoreInterface storeHouse;

    public ProductFactory(int number,StoreInterface storeHouse){
        this.number = number;
        this.storeHouse = storeHouse;
    }

    @Override
    public void run() {
        produce(number);
    }

    public void produce(int number){
        storeHouse.produce(number);
    }

}
