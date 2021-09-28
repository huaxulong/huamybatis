package com.huaxu.minimybatis.aop.proxy.demo4;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 2:31 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class LogIntercept implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("LogIntercept : begin Hello....");
        Object invoke = invocation.invoke();
        System.out.println("LogIntercept : After Hello....");
        return invoke;
    }
}
