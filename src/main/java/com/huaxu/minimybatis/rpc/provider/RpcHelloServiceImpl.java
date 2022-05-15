package com.huaxu.minimybatis.rpc.provider;

import com.huaxu.minimybatis.rpc.api.RpcHelloService;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 11:59 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class RpcHelloServiceImpl implements RpcHelloService {
    @Override
    public String hello(String name) {
        return "Hello" +name + "!";
    }
}
