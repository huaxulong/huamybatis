package com.huaxu.minimybatis.design.observer;

/**
 * @description: 抽象主题角色：具有注册观察者对象、删除观察者对象和通知所有观察者对象的功能; 也就是被观察者对象
 * @Author: Mr.Hua
 * @date: 2024/6/23 19:14
 */
public interface Subject {

    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers();

}
