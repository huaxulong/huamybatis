package com.huaxu.minimybatis.design.proxy;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/16 21:50
 */
public class GamePlayer implements IGamePlayer {

    private String name = "";

    public GamePlayer(String _name) {
        this.name = _name;
    }

    @Override
    public void login(String user, String password) {
        System.out.println("登录名为" + user + "的用户" + this.name + "登录成功");
    }

    @Override
    public void killBoss() {
        System.out.println(this.name + "在打怪");
    }

    @Override
    public void upgrade() {
        System.out.println(this.name + "升级了");
    }

    @Override
    public IGamePlayer getProxy() {
        return null;
    }
}
