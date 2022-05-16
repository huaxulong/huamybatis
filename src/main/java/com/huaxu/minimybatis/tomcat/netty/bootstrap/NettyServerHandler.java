package com.huaxu.minimybatis.tomcat.netty.bootstrap;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.huaxu.minimybatis.tomcat.bio.servlet.BaseServlet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.StringUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 9:19 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<Object> {

    public static Map<String, BaseServlet> servletMap = new ConcurrentHashMap<>();

    /*@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest req = (HttpRequest) msg;
            System.out.println("Http 请求报文完整版：" + req);

            Nrequest request = new Nrequest(ctx, req);
            Nresponse response = new Nresponse(ctx, req);

            // 实际处理业务
            String url = request.getUrl();
            if (!StringUtils.isEmpty(url) && servletMap.containsKey(url)) {
                servletMap.get(url).service(request, response);
            } else {
                response.write("404 - Not Found");
            }
        }
//        super.channelRead(ctx, msg);
    }*/

    private FullHttpResponse responseOk(HttpResponseStatus status, ByteBuf buf) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, buf);
        if (buf != null) {
            response.headers().set("Content-Type", "text/plain;charset=UTF-8");
            response.headers().set("Content-Length", response.content().readableBytes());
        }
        return response;
    }

    private ByteBuf copiedBuffer(String data, Charset utf8) {
        return Unpooled.wrappedBuffer(data.getBytes(utf8));
    }

    private Map<String, Object> getGetParamsFromChannel(FullHttpRequest fullHttpRequest) {
        Map<String, Object> params = new HashMap<>();
        if (fullHttpRequest.method() == HttpMethod.GET) {
            QueryStringDecoder decoder = new QueryStringDecoder(fullHttpRequest.uri());
            Map<String, List<String>> paramList = decoder.parameters();

            for (Map.Entry<String, List<String>> entry : paramList.entrySet()) {
                params.put(entry.getKey(), entry.getValue().get(0));
            }
        }
        return params;
    }

    private Map<String, Object> getPostParamsFromChannel(FullHttpRequest fullHttpRequest) {
        Map<String, Object> params = new HashMap<>();
        if (fullHttpRequest.method() == HttpMethod.POST) {
            String strContentType = fullHttpRequest.headers().get("Content-Type").trim();
            if (StringUtil.isNullOrEmpty(strContentType)) {
                return null;
            }
            if (strContentType.contains("form-data")) {
                params = getFormParams(fullHttpRequest);
            } else if (strContentType.contains("application/json")) {
                params = getJSONParams(fullHttpRequest);
            } else {
                return null;
            }
        }
        return params;
    }

    private Map<String, Object> getJSONParams(FullHttpRequest fullHttpRequest) {
        Map<String, Object> params = new HashMap<>();
        ByteBuf content = fullHttpRequest.content();
        byte[] reqContent = new byte[content.readableBytes()];
        content.readBytes(reqContent);

        String strContent = new String(reqContent, StandardCharsets.UTF_8);

        JSONObject jsonParams = JSON.parseObject(strContent);
        for (Object key : jsonParams.keySet()) {
            params.put(key.toString(), jsonParams.get(key));
        }
        return params;
    }

    private Map<String, Object> getFormParams(FullHttpRequest fullHttpRequest) {
        Map<String, Object> params = new HashMap<>();
        HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), fullHttpRequest);
        List<InterfaceHttpData> postData = decoder.getBodyHttpDatas();

        for (InterfaceHttpData data : postData) {
            if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                if (data instanceof MemoryAttribute) {
                    MemoryAttribute attribute = (MemoryAttribute) data;
                    params.put(attribute.getName(), attribute.getValue());
                }
            }
        }
        return params;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(channelHandlerContext, (FullHttpRequest) msg);
        }else if (msg instanceof WebSocketFrame){
            handleWebSocketFrame(channelHandlerContext, (WebSocketFrame)msg);
        }

    }

    private WebSocketServerHandshaker handshaker;

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        if ("websocket".equalsIgnoreCase(request.headers().get("Upgrade"))){
            WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                    "ws://localhost:8080/websocket", null, false);
            handshaker = wsFactory.newHandshaker(request);
            if (handshaker == null){
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            }else {
                handshaker.handshake(ctx.channel(), request);
            }
            return;
        }


        System.out.println("FullHttpRequest request:" + request);
        FullHttpResponse response = null;
        if (request.method() == HttpMethod.GET) {
            System.out.println(getGetParamsFromChannel(request));
            String responseData = "Get Method Done!";
            ByteBuf byteBuf = copiedBuffer(responseData, CharsetUtil.UTF_8);
            response = responseOk(HttpResponseStatus.OK, byteBuf);
        } else if (request.method() == HttpMethod.POST) {
            System.out.println(getPostParamsFromChannel(request));
            String responseData = "Post Method Done";
            ByteBuf buf = copiedBuffer(responseData, CharsetUtil.UTF_8);
            response = responseOk(HttpResponseStatus.OK, buf);
        } else {
            responseOk(HttpResponseStatus.INTERNAL_SERVER_ERROR, null);
        }
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), ((CloseWebSocketFrame) frame).retain());
            return;
        }
        // 判断是不是ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 本例程仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(String.format(
                    "%s frame types not supported", frame.getClass().getName()));
        }

        // 返回应答消息
        String request = ((TextWebSocketFrame) frame).text();
        System.out.println(ctx.channel() + " receiver " + request);
        ctx.channel().write(
                new TextWebSocketFrame(request
                        + " , 欢迎使用Netty WebSocket服务，现在时刻："
                        + new Date()));
    }
}
