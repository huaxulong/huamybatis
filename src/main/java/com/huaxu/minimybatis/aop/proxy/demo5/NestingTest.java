package com.huaxu.minimybatis.aop.proxy.demo5;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 3:47 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class NestingTest {

    public static void main(String[] args) {
        Animal catTarget = new Cat();
        JdkDynamicProxy jdkDynamicProxy = new JdkDynamicProxy(catTarget);
        jdkDynamicProxy.setTag("第一个代理类");

        //获取JDK 动态代理对象
        Animal catProxy = jdkDynamicProxy.getProxy();

        //catProxy.eat();
        //基于第一个代理对象的 代理对象
        JdkDynamicProxy jdkDynamicProxy1 = new JdkDynamicProxy(catProxy);
        jdkDynamicProxy1.setTag("第2个代理类");

        Animal catProxyProxy = jdkDynamicProxy1.getProxy();

        catProxyProxy.eat();

    }

}
