package com.huaxu.minimybatis.spi.dubbospi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

@SPI("spi")
public interface MyCar {

    /**
     * 汽车启动
     * @param url
     */
    @Adaptive({"A_name","B_name","name"})
    void run(URL url);

    /**
     * 汽车启动
     * @param url
     */
    @Adaptive({"A_name","B_name","name"})
    void openDoor(URL url);
}
