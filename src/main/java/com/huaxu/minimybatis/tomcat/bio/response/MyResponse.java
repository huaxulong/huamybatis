package com.huaxu.minimybatis.tomcat.bio.response;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 6:25 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class MyResponse {

    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public void write(String msg) throws IOException{
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(msg);
        outputStream.write(sb.toString().getBytes());
    }

}
