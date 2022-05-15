package com.huaxu.minimybatis.rpc.consumer;

import com.huaxu.minimybatis.rpc.api.RpcHelloService;
import com.huaxu.minimybatis.rpc.proxy.RpcProxy;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 10:39 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class RpcConsumer {

    public static void main(String[] args) {
        RpcHelloService rpcHello = RpcProxy.create(RpcHelloService.class);
        System.out.println(rpcHello.hello(" baby"));
    }

}
