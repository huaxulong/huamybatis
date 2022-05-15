package com.huaxu.minimybatis.rpc.proxy;

import com.huaxu.minimybatis.rpc.protocol.InvokerProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 11:51 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class RpcProxy {

    public static <T> T create(Class<T> clazz) {
        MethodProxy proxy = new MethodProxy(clazz);
        Class<?>[] interfaces = clazz.isInterface() ?
                new Class[]{clazz} :
                clazz.getInterfaces();

        T result = (T) Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, proxy);
        return result;
    }

    private static class MethodProxy implements InvocationHandler {

        private Class<?> clazz;

        public MethodProxy(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //if is a class
            if (Object.class.equals(method.getDeclaringClass())) {
                try {
                    return method.invoke(this, args);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                //if is a interface
            } else {
                return rpcInvoke(proxy, method, args);
            }
            return null;
        }

        private Object rpcInvoke(Object proxy, Method method, Object[] args) {
            //传输协议封装
            InvokerProtocol msg = new InvokerProtocol();
            msg.setClassName(this.clazz.getName());
            msg.setMethodName(method.getName());
            msg.setValues(args);
            msg.setParams(method.getParameterTypes());

            final RpcProxyHandler consumerHandler = new RpcProxyHandler();
            EventLoopGroup group = new NioEventLoopGroup();

            try {
                Bootstrap b = new Bootstrap();
                b.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();

                                /**
                                 * 自定义协议解码器
                                 *  maxFrameLength：框架的最大长度。如果帧的长度大于此值，则将抛出 TooLongFrameException。
                                 *  lengthFieldOffset：长度字段的偏移量：即对应的长度字段在整个消息数据中得位置
                                 *  lengthFieldLength：长度字段的长度：如：长度字段是 int 型表示，那么这个值就是 4（long 型就是 8）
                                 *  lengthAdjustment：要添加到长度字段值的补偿值
                                 *  initialBytesToStrip：从解码帧中去除的第一个字节数
                                 */
                                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                                //自定义协议编码器
                                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                                //对象参数类型编码器
                                pipeline.addLast("encoder", new ObjectEncoder());
                                //对象参数类型解码器
                                pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE,
                                        ClassResolvers.cacheDisabled(null)));
                                pipeline.addLast("handler", consumerHandler);
                            }
                        });
                ChannelFuture future = b.connect("127.0.0.1", 6380).sync();
                System.out.println("SocketChannel, writeAndFlush, msg:" + msg);
                future.channel().writeAndFlush(msg).sync();
                System.out.println("SocketChannel, writeAndFlush, closeFuture.sync");
                future.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
            }
            return consumerHandler.getResponse();
        }

    }


}
