package com.huaxu.minimybatis.tomcat.netty.response;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 8:27 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Nresponse {

    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public Nresponse(ChannelHandlerContext ctx, HttpRequest req){
        this.ctx = ctx;
        this.req = req;
    }

    public void write(String out){
        try {
            if (out == null || out.length() == 0){
                return;
            }
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(out.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            ctx.write(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            ctx.flush();
            ctx.close();
        }
    }

}
