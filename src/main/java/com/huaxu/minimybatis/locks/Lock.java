package com.huaxu.minimybatis.locks;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-04-11 4:15 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface Lock {

    void lock();

    void unlock();

}
