package com.huaxu.minimybatis.aop.proxy.demo4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: 代理类
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 2:17 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class ProxyInvocationHandler implements InvocationHandler {

    private Object target;

    private Interceptor interceptor;

    public ProxyInvocationHandler(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation = new Invocation(target, method, args);
        return interceptor.intercept(invocation);
    }

    public <T> T getProxy(){
        return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
