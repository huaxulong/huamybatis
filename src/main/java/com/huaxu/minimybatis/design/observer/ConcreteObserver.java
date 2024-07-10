package com.huaxu.minimybatis.design.observer;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/23 19:22
 */
public class ConcreteObserver implements Observer {
    @Override
    public void update() {
        // 具体观察者的更新操作
        System.out.println("具体观察者收到通知，进行更新操作...");
    }
}
