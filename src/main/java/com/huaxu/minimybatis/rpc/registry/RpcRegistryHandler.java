package com.huaxu.minimybatis.rpc.registry;

import com.huaxu.minimybatis.rpc.protocol.InvokerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 11:44 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class RpcRegistryHandler extends ChannelInboundHandlerAdapter {

    private String basePackage;

    public static Map<String,Object> registryMap = new ConcurrentHashMap<>();

    private List<String> classNames = new ArrayList<>();

    public RpcRegistryHandler(String basePackage) {

        this.basePackage = basePackage;

        scanner(basePackage);

        doRegister();
    }

    private void doRegister(){
        if (classNames.size()==0){
            return ;
        }
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                Class<?> interfaceClazz = clazz.getInterfaces()[0];
                registryMap.put(interfaceClazz.getName(),clazz.newInstance());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void scanner(String basePackage){
        URL resource = RpcRegistryHandler.class.getResource("/");
        if (resource != null){
            File dir = new File(resource.getFile() + "/com/huaxu/minimybatis/rpc/provider");
            Arrays.stream(dir.listFiles()).forEach(file -> {
                if (file.isDirectory()){
                    scanner(basePackage + "." + file.getName());
                }else {
                    classNames.add(basePackage + "." + file.getName().replace(".class", "").trim());
                }
            });
        }
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端接收channelActive 事件");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端接收channelInactive 事件");
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端接收channelRead 事件");
        if (msg instanceof InvokerProtocol){
            InvokerProtocol request = (InvokerProtocol)msg;
            if (registryMap.containsKey(request.getClassName())){
                Object clazz = registryMap.get(request.getClassName());
                Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParams());
                Object result = method.invoke(clazz, request.getValues());
                ctx.writeAndFlush(result);
            }
        }
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端接收channelReadComplete 事件");
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
