package com.huaxu.minimybatis.tomcat.netty.bootstrap;

import com.huaxu.minimybatis.tomcat.bio.servlet.BaseServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.net.ServerSocket;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 9:15 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class NettyBootStrap {

    private int port = 8080;

    private Properties properties = new Properties();

    public static void main(String[] args) {
        new NettyBootStrap().start();
    }

    private void start(){
        init();
        worker();
    }

    private void init(){
        try {
            String WEB_INF = System.getProperty("user.dir");
            FileInputStream fis = new FileInputStream(WEB_INF + "/target/huaxumybatis/WEB-INF/" + "application.properties");
            properties.load(fis);

            for (Object obj : properties.keySet()) {
                String key = obj.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = properties.getProperty(key);
                    String className = properties.getProperty(servletName + ".className");

                    BaseServlet servlet = (BaseServlet) Class.forName(className).newInstance();
                    NettyServerHandler.servletMap.put(url, servlet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void worker(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new HttpResponseEncoder());
                            ch.pipeline().addLast(new HttpRequestDecoder());
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 启动服务器
            ChannelFuture f = server.bind(port).sync();
            System.out.println("Tomcat is start, listening port is :" + port);
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }



}
