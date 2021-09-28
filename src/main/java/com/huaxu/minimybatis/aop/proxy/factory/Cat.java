package com.huaxu.minimybatis.aop.proxy.factory;

/**
 * @description: Cat
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 4:56 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Cat implements Animal {

    @Override
    public void eat() {
        System.out.println("cat 吃 喵粮 ～～");
    }

}
