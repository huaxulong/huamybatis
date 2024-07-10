package com.huaxu.minimybatis.design.factorymethod;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/23 22:51
 */
public class Client {

    public static void main(String[] args) {
        Creator creator = new ConcreteCreator1();
        Product factory = creator.factory();
        System.out.println(factory);
    }
}
