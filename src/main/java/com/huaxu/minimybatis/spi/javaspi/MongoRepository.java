package com.huaxu.minimybatis.spi.javaspi;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-06-05 1:04 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class MongoRepository implements IRepository{
    @Override
    public void save(String data) {
        System.out.println("Save " + data + " to Mongo");
    }
}
