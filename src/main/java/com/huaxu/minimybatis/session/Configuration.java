package com.huaxu.minimybatis.session;

import com.huaxu.minimybatis.binding.MapperRegistry;
import com.huaxu.minimybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 4:36 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Configuration {

    public static Properties PROPS = new Properties();

    /**
     * mapper 代理注册器
     */
    protected final MapperRegistry mapperRegistry = new MapperRegistry();

    /**
     * mapper 文件对应的sql语句
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public <T> void addMapper(Class<T> type){
        this.mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession){
        return this.mapperRegistry.getMapper(type, sqlSession);
    }

    public void addMappedStatement(String key, MappedStatement mappedStatement){
        this.mappedStatements.put(key, mappedStatement);
    }

    public MappedStatement getMappedStatement(String id){
        return this.mappedStatements.get(id);
    }

    public static String getProperty(String key) {
        return getProperty(key, "");
    }

    public static String getProperty(String key, String defaultValue){
        return PROPS.containsKey(key) ? PROPS.getProperty(key) : defaultValue;
    }

}
