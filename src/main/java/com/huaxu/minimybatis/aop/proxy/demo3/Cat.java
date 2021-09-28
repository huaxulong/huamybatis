package com.huaxu.minimybatis.aop.proxy.demo3;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 11:32 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("猫吃 猫粮 ～～～");
    }
}
