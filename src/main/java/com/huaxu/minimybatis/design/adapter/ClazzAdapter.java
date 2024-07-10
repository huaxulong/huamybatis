package com.huaxu.minimybatis.design.adapter;

/**
 * @description: 类适配
 * @Author: Mr.Hua
 * @date: 2024/6/23 20:51
 */
public class ClazzAdapter {

    public static void main(String[] args) {
        Target adaptee = new Adapter();
        adaptee.sampleOperation1();
        adaptee.sampleOperation2();

        // 类适配器
        Target adapter = new Adapter1(new Adaptee());
        adapter.sampleOperation1();
        adapter.sampleOperation2();
    }

}
