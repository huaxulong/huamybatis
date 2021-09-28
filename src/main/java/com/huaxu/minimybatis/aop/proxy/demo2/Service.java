package com.huaxu.minimybatis.aop.proxy.demo2;

/**
 * @description: Service
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 10:50 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Service {

    /**
     *  final 方法不能被子类覆盖
     */
    public final void finalMethod() {
        System.out.println("Service.finalMethod 执行了");
    }

    public void publicMethod() {
        System.out.println("Service.publicMethod 执行了");
    }

}
