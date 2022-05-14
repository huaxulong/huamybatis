package com.huaxu.minimybatis.tomcat.netty.request;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 8:27 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Nrequest {

    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public Nrequest(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public String getUrl() {
        return req.uri();
    }

    public String getMethod() {
        return req.method().name();
    }

    public String getParameter(String name) {
        Map<String, List<String>> params = getParameters();
        List<String> param = params.get(name);
        if (null != param) {
            return param.get(0);
        }
        return null;
    }

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
        return decoder.parameters();
    }

}
