package com.huaxu.minimybatis.design.factory;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/16 20:07
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
