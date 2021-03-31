package com.huaxu.minimybatis.executor.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 9:50 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface StatementHandler {

    /**
     * SQL 预处理
     * @param connection
     * @return
     * @throws SQLException
     */
    PreparedStatement prepare(Connection connection) throws SQLException;

    /**
     * 查询数据库
     * @param preparedStatement
     * @return
     * @throws SQLException
     */
    ResultSet query(PreparedStatement preparedStatement) throws SQLException;

    /**
     * 更新数据库
     * @param preparedStatement
     * @throws SQLException
     */
    int update(PreparedStatement preparedStatement) throws SQLException;

}
