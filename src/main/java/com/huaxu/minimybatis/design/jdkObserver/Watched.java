package com.huaxu.minimybatis.design.jdkObserver;

import java.util.Observable;

/**
 * @description: 被观察者对象
 * @Author: Mr.Hua
 * @date: 2024/6/23 19:52
 */
public class Watched extends Observable {

    private String data = "";

    public String retrieveData() {
        return data;
    }

    public void changeData(String data) {
        if (!this.data.equalsIgnoreCase(data)) {
            this.data = data;
            setChanged();
        }
        notifyObservers();
    }

}
