package com.huaxu.minimybatis.design.factory;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/16 20:16
 */
public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape shape1 = factory.getShape("CIRCLE");
        shape1.draw();

        Shape shape2 = factory.getShape("RECTANGLE");
        shape2.draw();

        Shape shape3 = factory.getShape("SQUARE");
        shape3.draw();
    }
}
