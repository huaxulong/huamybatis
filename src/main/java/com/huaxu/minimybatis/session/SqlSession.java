package com.huaxu.minimybatis.session;

import java.util.List;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-28 4:41 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface SqlSession {

    /**
     * 获取 mapper
     * @param paramClass
     * @param <T>
     * @return
     */
    <T>T getMapper(Class<T> paramClass);



    /**
     * 查询带条记录
     *
     * @param statementId
     * @param parameter
     * @return
     * @see
     */
    <T> T selectOne(String statementId, Object parameter);

    /**
     * 查询多条记录
     *
     * @param statementId
     * @param parameter
     * @return
     * @see
     */
    <E> List<E> selectList(String statementId, Object parameter);

    /**
     * update
     *
     * @param statementId
     * @param parameter
     */
    void update(String statementId, Object parameter);


    /**
     * insert
     *
     * @param statementId
     * @param parameter
     */
    void insert(String statementId, Object parameter);

    /**
     * 获取配置类
     * @return
     */
    Configuration getConfiguration();

}
