package com.huaxu.minimybatis.executor.resultSet;

import com.huaxu.minimybatis.mapping.MappedStatement;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: DefaultResultSetHandler
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-31 1:06 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class DefaultResultSetHandler<E> implements ResultSetHandler{

    private final MappedStatement mappedStatement;

    public DefaultResultSetHandler(MappedStatement mappedStatement) {
        this.mappedStatement = mappedStatement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> handlerResultSets(ResultSet resultSet) {
        try{
            List<E> result = new ArrayList<>();
            if (null == resultSet){
                return null;
            }

            while (resultSet.next()){
                // 通过反射实例化返回类
                Class<?> entityClass = Class.forName(mappedStatement.getResultType());
                E entity = (E)entityClass.newInstance();
                Field[] declaredFields = entityClass.getDeclaredFields();

                for (Field field : declaredFields){
                    // 对成员变量赋值
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();

                    // 目前只实现了string和int转换
                    if (String.class.equals(fieldType)){
                        field.set(entity, resultSet.getString(field.getName()));
                    }
                    else if (int.class.equals(fieldType) || Integer.class.equals(fieldType)){
                        field.set(entity, resultSet.getInt(field.getName()));
                    }
                    else{
                        // 其他类型自己转换，这里就直接设置了
                        field.set(entity, resultSet.getObject(field.getName()));
                    }
                }
                result.add(entity);
            }
            return result;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
