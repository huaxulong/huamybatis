package com.huaxu.minimybatis.executor.parameter;

import java.sql.PreparedStatement;

/**
 * @description: ParameterHandler
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-31 12:49 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface ParameterHandler {

    /**
     * 对参数进行设置值
     * @param preparedStatement
     */
    void setParameter(PreparedStatement preparedStatement);

}
