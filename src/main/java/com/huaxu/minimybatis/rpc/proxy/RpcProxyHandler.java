package com.huaxu.minimybatis.rpc.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.Getter;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 11:54 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
@Getter
public class RpcProxyHandler extends ChannelInboundHandlerAdapter {

    private Object response;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        response = msg;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client exception is general");
    }

}
