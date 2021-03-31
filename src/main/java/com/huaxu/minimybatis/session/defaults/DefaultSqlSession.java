package com.huaxu.minimybatis.session.defaults;

import com.huaxu.minimybatis.mapping.MappedStatement;
import com.huaxu.minimybatis.session.Configuration;
import com.huaxu.minimybatis.session.SqlSession;
import com.huaxu.minimybatis.executor.Executor;
import com.huaxu.minimybatis.executor.SimpleExecutor;
import com.huaxu.minimybatis.utils.CommonUtis;

import java.util.List;

/**
 * @description: DefaultSqlSession
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 4:42 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    private final Executor executor;


    public DefaultSqlSession(Configuration configuration){
        this.configuration = configuration;
        this.executor = new SimpleExecutor(configuration);
    }

    @Override
    public <T> T getMapper(Class<T> paramClass) {
        return this.configuration.getMapper(paramClass, this);
    }

    @Override
    public <T> T selectOne(String statementId, Object parameter) {
        List<T> results = this.selectList(statementId, parameter);
        return CommonUtis.isNotEmpty(results)? results.get(0) : null;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object parameter) {
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        return this.executor.doQuery(mappedStatement, parameter);
    }

    @Override
    public void update(String statementId, Object parameter) {
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        this.executor.doUpdate(mappedStatement, parameter);
    }

    @Override
    public void insert(String statementId, Object parameter) {
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        this.executor.doUpdate(mappedStatement, parameter);
    }

    @Override
    public int delete(String statementId, Object parameter) {
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        return this.executor.doUpdate(mappedStatement, parameter);
    }

    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }
}
