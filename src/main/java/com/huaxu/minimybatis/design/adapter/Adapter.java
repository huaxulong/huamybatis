package com.huaxu.minimybatis.design.adapter;

/**
 * @description: 适配器角色
 * @Author: Mr.Hua
 * @date: 2024/6/23 20:47
 */
public class Adapter extends Adaptee implements Target {
    @Override
    public void sampleOperation2() {
        System.out.println("Adapter.sampleOperation2");
    }
}
