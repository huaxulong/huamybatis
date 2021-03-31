package com.huaxu.minimybatis.executor.resultSet;

import java.sql.ResultSet;
import java.util.List;

/**
 * @description: ResultSetHandler
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-31 1:07 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface ResultSetHandler<E> {

    List<E> handlerResultSets(ResultSet resultSet);

}
