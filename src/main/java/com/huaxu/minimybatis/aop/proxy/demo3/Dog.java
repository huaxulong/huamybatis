package com.huaxu.minimybatis.aop.proxy.demo3;

/**
 * @description: Dog
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 2:07 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Dog implements Animal{
    @Override
    public void eat() {
        System.out.println("狗吃狗粮 — ～～～");
    }
}
