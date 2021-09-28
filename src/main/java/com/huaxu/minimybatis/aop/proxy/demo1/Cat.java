package com.huaxu.minimybatis.aop.proxy.demo1;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 10:22 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Cat implements Animal{
    @Override
    public void eat() {
        System.out.println("猫吃 猫粮 ～～～");
    }

    /*@Override
    public String toString() {
        System.out.println("进入toString...");
        return "toString";
    }*/

}
