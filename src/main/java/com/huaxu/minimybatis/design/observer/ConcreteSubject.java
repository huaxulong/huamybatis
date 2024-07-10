package com.huaxu.minimybatis.design.observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/23 19:19
 */
public class ConcreteSubject implements Subject{

    private Vector<Observer> observersVector = new Vector<>();

    @Override
    public void registerObserver(Observer observer) {
        observersVector.addElement(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observersVector.removeElement(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> observers = getObservers();

        while (observers.hasMoreElements()) {
            Observer observer = observers.nextElement();
            observer.update();
        }
    }

    private Enumeration<Observer> getObservers() {
        return ((Vector<Observer>)observersVector.clone()).elements();
    }
}
