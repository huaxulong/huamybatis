package com.huaxu.minimybatis.executor;

import com.huaxu.minimybatis.mapping.MappedStatement;

import java.util.List;

/**
 * @description: Executor
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 4:46 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface Executor {

    int doUpdate(MappedStatement mappedStatement, Object parameter);

    <E> List<E> doQuery(MappedStatement mappedStatement, Object parameter);
}
