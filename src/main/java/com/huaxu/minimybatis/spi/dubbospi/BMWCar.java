package com.huaxu.minimybatis.spi.dubbospi;

import com.alibaba.dubbo.common.URL;

public class BMWCar implements MyCar{
    @Override
    public void run(URL url) {
        System.out.println("宝马车发动");
    }

    @Override
    public void openDoor(URL url) {
        System.out.println("宝马车开门");
    }
}
