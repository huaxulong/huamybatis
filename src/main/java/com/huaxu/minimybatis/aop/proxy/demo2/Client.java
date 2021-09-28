package com.huaxu.minimybatis.aop.proxy.demo2;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 11:11 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Client {

    public static void main(String[] args) {
        Service target = new Service();

        CglibDynamicProxy proxy = new CglibDynamicProxy(target);

        Service proxyObj = proxy.getProxy();

        proxyObj.finalMethod();

        proxyObj.publicMethod();
    }

}
