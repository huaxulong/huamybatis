package com.huaxu.minimybatis.tomcat.bio.bootstrap;

import com.huaxu.minimybatis.tomcat.bio.request.MyRequest;
import com.huaxu.minimybatis.tomcat.bio.response.MyResponse;
import com.huaxu.minimybatis.tomcat.bio.servlet.BaseServlet;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 6:34 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class HdxTomcat {

    private int port = 8080;

    private ServerSocket serverSocket;

    private Map<String, BaseServlet> servletMap = new ConcurrentHashMap<>();

    private Properties properties = new Properties();

    public static void main(String[] args) {
        new HdxTomcat().start();
    }

    private void start() {
        init();
        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("tomcat is start , listening port is " + port);
            for (; ; ) {
                Socket socket = serverSocket.accept();

                process(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
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
                    servletMap.put(url, servlet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(Socket socket) throws Exception {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        MyRequest request = new MyRequest(inputStream);
        MyResponse response = new MyResponse(outputStream);

        String url = request.getUrl();
        if (!StringUtils.isEmpty(url) && servletMap.containsKey(url)) {
            servletMap.get(url).service(request, response);
        } else {
            response.write("404 not Found");
        }
        outputStream.flush();
        inputStream.close();
        outputStream.close();
    }

}
