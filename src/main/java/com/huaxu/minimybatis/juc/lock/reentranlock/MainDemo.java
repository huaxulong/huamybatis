package com.huaxu.minimybatis.juc.lock.reentranlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-06 7:17 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class MainDemo {

    public static ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        ReentrantlockStore storeHouse = new ReentrantlockStore();
        for(int i =0;i<10;i++){
            threadPoolExecutor.submit(new ConsumerFactory(5,storeHouse));
            threadPoolExecutor.submit(new ProductFactory(3,storeHouse));

        }

        System.out.println("**************分割线****************");
        ReentrantlockStore reentrantlockStore = new ReentrantlockStore();
        for(int i =0;i<10;i++){
            threadPoolExecutor.submit(new ConsumerFactory(5,reentrantlockStore));
            threadPoolExecutor.submit(new ProductFactory(3,reentrantlockStore));
        }


    }

}
