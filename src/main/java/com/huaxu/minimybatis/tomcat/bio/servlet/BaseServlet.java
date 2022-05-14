package com.huaxu.minimybatis.tomcat.bio.servlet;

import com.huaxu.minimybatis.tomcat.bio.request.MyRequest;
import com.huaxu.minimybatis.tomcat.bio.response.MyResponse;
import com.huaxu.minimybatis.tomcat.netty.request.Nrequest;
import com.huaxu.minimybatis.tomcat.netty.response.Nresponse;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 6:13 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public abstract class BaseServlet {

    public void service(MyRequest request, MyResponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public void service(Nrequest request, Nresponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request, response);
        }else {
            doPost(request, response);
        }
    }

    protected abstract void doPost(MyRequest request, MyResponse response) throws Exception;

    protected abstract void doGet(MyRequest request, MyResponse response) throws Exception;

    protected abstract void doPost(Nrequest request, Nresponse response) throws Exception;

    protected abstract void doGet(Nrequest request, Nresponse response) throws Exception;
}
