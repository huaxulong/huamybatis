package com.huaxu.minimybatis.design.proxy;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/23 20:30
 */
public class ProxySubject extends Subject {

    public ProxySubject() {
    }

    private RealSubject realSubject;

    @Override
    protected void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.request();
        postRequest();
    }

    private void preRequest() {
        System.out.println("pre request");
    }

    private void postRequest() {
        System.out.println("post request");
    }
}
