package com.huaxu.minimybatis.design.adapter;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/23 21:05
 */
public class Adapter1 implements Target {

    private Adaptee adaptee;

    public Adapter1(Adaptee adaptee) {
        super();
        this.adaptee = adaptee;
    }

    @Override
    public void sampleOperation1() {
        adaptee.sampleOperation1();
    }

    @Override
    public void sampleOperation2() {
        System.out.println("Adapter1 sampleOperation2");
    }
}
