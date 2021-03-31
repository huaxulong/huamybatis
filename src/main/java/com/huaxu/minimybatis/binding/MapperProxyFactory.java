package com.huaxu.minimybatis.binding;

import com.huaxu.minimybatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * @description: MapperProxyFactory
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 5:48 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface){
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession){
        MapperProxy<T> mapperProxy = new MapperProxy(sqlSession, this.mapperInterface);
        return newInstance(mapperProxy);
    }

    /**
     * 根据 mapper 代理返回对应的实例
     * @param mapperProxy
     * @return
     */
    private T newInstance(MapperProxy<T> mapperProxy){
        return (T)Proxy.newProxyInstance(this.mapperInterface.getClassLoader(), new Class[]{this.mapperInterface}, mapperProxy);
    }

}
