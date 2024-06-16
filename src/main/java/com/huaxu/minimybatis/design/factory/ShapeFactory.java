package com.huaxu.minimybatis.design.factory;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/16 20:11
 */
public class ShapeFactory {
    public Shape getShape(String shapeType) {
        if ("CIRCLE".equalsIgnoreCase(shapeType)) {
            return new Circle();
        }
        if ("RECTANGLE".equalsIgnoreCase(shapeType)) {
            return new Rectangle();
        }
        if ("SQUARE".equalsIgnoreCase(shapeType)) {
            return new Square();
        }
        return null;
    }
}
