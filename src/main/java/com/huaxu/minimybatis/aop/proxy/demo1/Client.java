package com.huaxu.minimybatis.aop.proxy.demo1;

/**
 * @description: Client
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 10:32 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        Cat catTarget = new Cat();

        JdkDynamicProxy proxy = new JdkDynamicProxy(catTarget);

        Animal catProxy = proxy.getProxy();

        catProxy.eat();
    }

}
