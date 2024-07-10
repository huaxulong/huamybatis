package com.huaxu.minimybatis.design.factorymethod;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/23 22:53
 */
public class ConcreteCreator1 implements Creator {
    @Override
    public Product factory() {
        return new ConcreteProduct1();
    }
}
