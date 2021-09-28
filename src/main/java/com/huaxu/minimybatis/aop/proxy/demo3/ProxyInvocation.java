package com.huaxu.minimybatis.aop.proxy.demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 11:37 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class ProxyInvocation implements InvocationHandler {

    private Object target;

    private List<Interceptor> beforeInterceptors;

    private List<Interceptor> afterInterceptors;


    public ProxyInvocation(Object target, List<Interceptor> beforeInterceptors, List<Interceptor> afterInterceptors) {
        this.target = target;
        this.beforeInterceptors = beforeInterceptors;
        this.afterInterceptors = afterInterceptors;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (Interceptor interceptor : beforeInterceptors) {
            interceptor.intercept();
        }
        Object result = method.invoke(target, args);
        for (Interceptor interceptor : afterInterceptors) {
            interceptor.intercept();
        }
        return result;
    }

    public <T> T getProxyObject(){
        return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
