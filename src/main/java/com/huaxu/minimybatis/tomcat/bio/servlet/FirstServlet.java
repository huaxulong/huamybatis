package com.huaxu.minimybatis.tomcat.bio.servlet;

import com.huaxu.minimybatis.tomcat.bio.request.MyRequest;
import com.huaxu.minimybatis.tomcat.bio.response.MyResponse;
import com.huaxu.minimybatis.tomcat.netty.request.Nrequest;
import com.huaxu.minimybatis.tomcat.netty.response.Nresponse;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 6:30 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class FirstServlet extends BaseServlet {

    @Override
    protected void doPost(MyRequest request, MyResponse response) throws Exception {
        response.write("This is the Post Response (reggen:html)");
    }

    @Override
    protected void doGet(MyRequest request, MyResponse response) throws Exception {
        response.write("This is the First Servlet");
    }

    @Override
    protected void doPost(Nrequest request, Nresponse response) throws Exception {
        response.write("{\"callId\":null,\"success\":true,\"errorCode\":\"0\",\"errorMsg\":\"success\",\"result\":\"netty post json\"}");
    }

    @Override
    protected void doGet(Nrequest request, Nresponse response) throws Exception {
        response.write("{\"callId\":null,\"success\":true,\"errorCode\":\"0\",\"errorMsg\":\"success\",\"result\":\"netty get json\"}");
    }


}
