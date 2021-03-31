package com.huaxu.minimybatis.binding;

import com.huaxu.minimybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: MapperRegistry
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 5:42 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class MapperRegistry {

    private final Map<Class<?>, MapperProxyFactory> knownMappers = new HashMap<>();

    public <T> void addMapper(Class<T> type){
        this.knownMappers.put(type, new MapperProxyFactory<T>(type));
    }

    public <T> T getMapper(Class<T> type , SqlSession sqlSession){
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) this.knownMappers.get(type);
        return mapperProxyFactory.newInstance(sqlSession);
    }

}
