package com.huaxu.minimybatis.executor.parameter;

import java.sql.PreparedStatement;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-31 12:51 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class DefaultParameterHandler implements ParameterHandler{

    private Object parameter;

    public DefaultParameterHandler(Object parameter){
        this.parameter = parameter;
    }

    @Override
    public void setParameter(PreparedStatement ps) {
        try {
            if(null != parameter){
                if (parameter.getClass().isArray()) {
                    Object[] params = (Object[]) parameter;
                    for (int i = 0; i < params.length; i++) {
                        //Mapper保证传入参数类型匹配，这里就不做类型转换了
                        // 如果是基本数据类型 或者 string

                        // 如果不是基本数据类型

                        ps.setObject(i +1, params[i]);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
