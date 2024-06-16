package com.huaxu.minimybatis.design.proxy;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/16 21:44
 */
public interface IGamePlayer {

    void login(String user, String password);

    void killBoss();

    void upgrade();

    IGamePlayer getProxy();
}
