package com.huaxu.minimybatis.design.abstractfactory;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/23 19:11
 */
public class AbsFctoryClient {

    public static void main(String[] args) {
        ConcreteCreator concreteCreator = new ConcreteCreator();
        ProductA productA = concreteCreator.factoryA();

        ProductB productB = concreteCreator.factoryB();

        System.out.println(productA);
        System.out.println(productB);
    }

}
