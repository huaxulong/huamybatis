package com.huaxu.minimybatis.tomcat.bio.request;

import java.io.InputStream;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 6:15 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class MyRequest {

    private String method;

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    private String url;

    public MyRequest(InputStream in){
        try {
            String context = "";
            byte[] buffer = new byte[1024];
            int len = 0;
            if ((len = in.read(buffer)) > 0){
                context = new String(buffer, 0, len);
            }
            String line = context.split("\\n")[0];
            String[] arr = line.split("\\s");
            if (arr.length >= 2){
                this.method = arr[0];
                this.url = arr[1].split("\\?")[0];
                System.out.println("context : "+context);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
