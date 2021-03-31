package com.huaxu.minimybatis.session;

import com.huaxu.minimybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description: SqlSessionFactoryBuilder
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 4:32 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(String fileName){
        InputStream inputStream = SqlSessionFactoryBuilder.class.getClassLoader().getResourceAsStream(fileName);
        return build(inputStream);
    }

    public SqlSessionFactory build(InputStream inputStream){
        try {
            Configuration.PROPS.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DefaultSqlSessionFactory(new Configuration());
    }

}
