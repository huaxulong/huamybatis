package com.huaxu.minimybatis.executor;

import com.huaxu.minimybatis.constants.Constant;
import com.huaxu.minimybatis.executor.parameter.DefaultParameterHandler;
import com.huaxu.minimybatis.executor.parameter.ParameterHandler;
import com.huaxu.minimybatis.executor.resultSet.DefaultResultSetHandler;
import com.huaxu.minimybatis.executor.resultSet.ResultSetHandler;
import com.huaxu.minimybatis.executor.statement.SimpleStatementHandler;
import com.huaxu.minimybatis.executor.statement.StatementHandler;
import com.huaxu.minimybatis.mapping.MappedStatement;
import com.huaxu.minimybatis.session.Configuration;

import java.sql.*;
import java.util.List;

/**
 * @description: SimpleExecutor
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 4:50 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class SimpleExecutor implements Executor{

    private static Connection connection;

    private Configuration configuration;

    public SimpleExecutor(Configuration configuration){
        this.configuration = configuration;
    }

    static {
        initConnect();
    }

    @Override
    public <E> List<E> doQuery(MappedStatement ms, Object parameter) {
        try {
            Connection connection = getConnect();
            MappedStatement mappedStatement = configuration.getMappedStatement(ms.getSqlId());
            // 实例化StatementHandler 对象
            StatementHandler statementHandler = new SimpleStatementHandler(mappedStatement);
            PreparedStatement preparedStatement = statementHandler.prepare(connection);
            // 对参数进行设值
            ParameterHandler parameterHandler = new DefaultParameterHandler(parameter);
            parameterHandler.setParameter(preparedStatement);

            ResultSet resultSet = statementHandler.query(preparedStatement);

            // 通过反射将ResultSet中结果设置到目标resultType对象中
            ResultSetHandler resultSetHandler = new DefaultResultSetHandler(mappedStatement);
            return resultSetHandler.handlerResultSets(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void doUpdate(MappedStatement mappedStatement, Object parameter) {

    }

    public Connection getConnect() throws SQLException{
        if (null != connection) {
            return connection;
        }else {
            throw new SQLException("无法链接数据库 ");
        }
    }

    private static void initConnect(){
        String driver = Configuration.PROPS.getProperty(Constant.DB_DRIVER_CONF);
        String url = Configuration.PROPS.getProperty(Constant.DB_URL_CONF);
        String username = Configuration.PROPS.getProperty(Constant.DB_USERNAME_CONF);
        String password = Configuration.PROPS.getProperty(Constant.DB_PASSWORD);

        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
