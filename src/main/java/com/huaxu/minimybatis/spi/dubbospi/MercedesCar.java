package com.huaxu.minimybatis.spi.dubbospi;

import com.alibaba.dubbo.common.URL;

public class MercedesCar implements MyCar{
    @Override
    public void run(URL url) {
        System.out.println("奔驰车发动");
    }

    @Override
    public void openDoor(URL url) {
        System.out.println("奔驰车开门");
    }
}
