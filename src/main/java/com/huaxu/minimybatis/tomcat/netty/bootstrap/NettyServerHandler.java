package com.huaxu.minimybatis.tomcat.netty.bootstrap;

import com.huaxu.minimybatis.tomcat.bio.servlet.BaseServlet;
import com.huaxu.minimybatis.tomcat.netty.request.Nrequest;
import com.huaxu.minimybatis.tomcat.netty.response.Nresponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 9:19 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    public static Map<String, BaseServlet> servletMap = new ConcurrentHashMap<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest){
            HttpRequest req = (HttpRequest) msg;
            System.out.println("Http 请求报文完整版：" + req);

            Nrequest request = new Nrequest(ctx, req);
            Nresponse response = new Nresponse(ctx, req);

            // 实际处理业务
            String url = request.getUrl();
            if (!StringUtils.isEmpty(url) && servletMap.containsKey(url)){
                servletMap.get(url).service(request, response);
            }else {
                response.write("404 - Not Found");
            }
        }
//        super.channelRead(ctx, msg);
    }
}
