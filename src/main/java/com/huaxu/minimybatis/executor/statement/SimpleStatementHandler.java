package com.huaxu.minimybatis.executor.statement;

import com.huaxu.minimybatis.mapping.MappedStatement;
import com.huaxu.minimybatis.utils.CommonUtis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-31 12:29 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class SimpleStatementHandler implements StatementHandler{

    /** #{}正则匹配 */
    private static Pattern param_pattern = Pattern.compile("#\\{([^\\{\\}]*)\\}");

    private MappedStatement mappedStatement;

    public SimpleStatementHandler(MappedStatement mappedStatement){
        this.mappedStatement = mappedStatement;
    }

    @Override
    public PreparedStatement prepare(Connection connection) throws SQLException {
        String originalSql = mappedStatement.getSql();
        if (CommonUtis.isNotEmpty(originalSql)) {
            return connection.prepareStatement(convert(originalSql));
        }
        throw new SQLException("无法生成PreparedStatement ");
    }

    @Override
    public ResultSet query(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }

    @Override
    public void update(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.executeUpdate();
    }

    /**
     *  将SQL语句中的#{}替换为？，源码中是在SqlSourceBuilder类中解析的
     * @param source
     * @return
     */
    public String convert(String source){
        source = source.trim();
        Matcher matcher = param_pattern.matcher(source);
        return matcher.replaceAll("?");
    }
}
