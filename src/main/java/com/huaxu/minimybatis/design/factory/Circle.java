package com.huaxu.minimybatis.design.factory;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/16 20:09
 */
public class Circle implements Shape
{
    @Override
    public void draw()
    {
        System.out.println("Inside Circle::draw() method.");
    }
}
