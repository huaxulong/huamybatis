package com.huaxu.minimybatis.design.proxy;


/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/23 20:31
 */
public class ProxyClient {
    public static void main(String[] args) {
        // 创建一个代理对象
        Subject subject = new ProxySubject();
        // 通过代理对象，调用目标对象的方法
        subject.request();
    }

}
