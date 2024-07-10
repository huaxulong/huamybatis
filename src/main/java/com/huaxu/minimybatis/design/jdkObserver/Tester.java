package com.huaxu.minimybatis.design.jdkObserver;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/23 19:50
 */
public class Tester {

    private static Watched watched;

    public static void main(String[] args) {
        // 被观察者
        watched = new Watched();

        // 观察者
        new Watcher(watched);

        watched.changeData("IM C");
        watched.changeData("IM Java");
        watched.changeData("IM PY");
        watched.changeData("IM VB");
    }

}
