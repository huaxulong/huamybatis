package com.huaxu.minimybatis.design.proxy;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/23 20:29
 */
public class RealSubject extends Subject{
    @Override
    protected void request() {
        System.out.println("RealSubject ing");
    }
}
