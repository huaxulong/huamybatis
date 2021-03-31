package com.huaxu.minimybatis.session;

/**
 * @description: SqlSessionFactory
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 4:31 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface SqlSessionFactory {

    SqlSession openSession();

}
