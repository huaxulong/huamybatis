package com.huaxu.minimybatis.test.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.huaxu.minimybatis.spi.dubbospi.DemoSpi;
import com.huaxu.minimybatis.spi.dubbospi.MyCar;
import org.junit.Test;

public class DubboSPITest {

    @Test
    public void syaHello() {
        ExtensionLoader<DemoSpi> extensionLoader = ExtensionLoader.getExtensionLoader(DemoSpi.class);
        DemoSpi demoSpi = extensionLoader.getExtension("demoSpiImpl");
        demoSpi.say();
    }

    @Test
    public void dubboAdaptiveTest() {
        ExtensionLoader<MyCar> loader = ExtensionLoader.getExtensionLoader(MyCar.class);
        MyCar car = loader.getAdaptiveExtension();

        URL url = URL.valueOf("http://localhost:9999/xxx?name=Mercedes&B_name=BMW");
        car.run(url);
        car.openDoor(URL.valueOf("http://localhost:9999/xxx?A_name=Mercedes&B_name=BMW"));
    }

}
