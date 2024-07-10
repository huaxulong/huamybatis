package com.huaxu.minimybatis.design.observer;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/23 19:17
 */
public class ObserverClient {

    static Subject subject = new ConcreteSubject();
    static Observer observer = new ConcreteObserver();

    public static void main(String[] args) {

        subject.registerObserver(observer);

        Observer observer1 = new ConcreteObserver();
        subject.registerObserver(observer1);


        subject.notifyObservers();
    }

}
