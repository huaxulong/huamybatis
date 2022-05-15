package com.huaxu.minimybatis.rpc.api;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 10:31 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface RpcService {

    int add(int a, int b);

    int sub(int a, int b);

    /**
     * 乘
     *
     * @param a
     * @param b
     * @return
     */
    int mult(int a, int b);

    /**
     * 除
     *
     * @param a
     * @param b
     * @return
     */
    int div(int a, int b);

}
