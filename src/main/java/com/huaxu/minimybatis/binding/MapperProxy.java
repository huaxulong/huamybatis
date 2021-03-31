package com.huaxu.minimybatis.binding;

import com.huaxu.minimybatis.mapping.MappedStatement;
import com.huaxu.minimybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @description: MapperProxy    /  动态代理对象
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 5:58 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -9091782488431667005L;

    /**
     * 被代理对象
     */
    private final SqlSession sqlSession;

    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface){
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this, args);
        }
        return this.execute(method, args);
    }

    private Object execute(Method method, Object[] args){
        String statementId = this.mapperInterface.getName() + "." + method.getName();
        MappedStatement ms = this.sqlSession.getConfiguration().getMappedStatement(statementId);

        Object result = null;
        switch (ms.getSqlCommandType()){
            case SELECT:{
                Class<?> returnType = method.getReturnType();
                //如果返回结果是list, 应该调用查询多个结果的方法，否则只要查单条记录
                if(Collection.class.isAssignableFrom(returnType)){
                    result = sqlSession.selectList(statementId, args);
                }else {
                    result = sqlSession.selectOne(statementId, args);
                }
                break;
            }
            case INSERT:{
                sqlSession.update(statementId, args);
                break;
            }
            case UPDATE:{
                break;
            }
            case DEFAULT:{

            }
        }
        return result;
    }


}
