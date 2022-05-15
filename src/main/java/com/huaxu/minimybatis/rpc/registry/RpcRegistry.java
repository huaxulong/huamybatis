package com.huaxu.minimybatis.rpc.registry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.Data;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 10:40 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
@Data
public class RpcRegistry {

    private static final String ENCODER = "encoder";

    private static final String DECODER = "decoder";

    private int port;

    public RpcRegistry(int port) {
        this.port = port;
    }

    private void start() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            /**
                             * 自定义协议解码器
                             * maxFrameLength：框架的最大长度。如果帧的长度大于此值，则将抛出 TooLongFrameException。
                             * lengthFieldOffset：长度字段的偏移量：即对应的长度字段在整个消息数据中得位置
                             * lengthFieldLength：长度字段的长度。如：长度字段是 int 型表示，那么这个值就是 4（long 型就是 8）
                             * lengthAdjustment：要添加到长度字段值的补偿值
                             * initialBytesToStrip：从解码帧中去除的第一个字节数
                             */
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                            //自定义协议编码器
                            pipeline.addLast(new LengthFieldPrepender(4));
                            //对象参数类型编码器
                            pipeline.addLast(ENCODER, new ObjectEncoder());
                            //对象参数类型解码器
                            pipeline.addLast(DECODER, new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                            pipeline.addLast(new RpcRegistryHandler("com.huaxu.minimybatis.rpc.provider"));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println("RpcRegistry bind 启动完成");
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        new RpcRegistry(6380).start();
    }

}
