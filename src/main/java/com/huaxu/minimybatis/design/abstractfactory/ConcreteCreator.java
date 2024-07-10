package com.huaxu.minimybatis.design.abstractfactory;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/23 18:50
 */
public class ConcreteCreator implements Creator{
    @Override
    public ProductA factoryA() {
        return new ProductA1();
    }

    @Override
    public ProductB factoryB() {
        return new ProductB1();
    }
}
