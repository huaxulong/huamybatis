package com.huaxu.minimybatis.design.jdkObserver;


import java.util.Observable;
import java.util.Observer;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/23 19:59
 */
public class Watcher implements Observer {

    /**
     * 构造器
     * @param w
     */
    public Watcher(Watched w) {
        w.addObserver(this);
    }

    @Override
    public void update(Observable ob, Object arg) {
        System.out.println("Data has been changed," + ((Watched)ob).retrieveData() + "");
    }
}
