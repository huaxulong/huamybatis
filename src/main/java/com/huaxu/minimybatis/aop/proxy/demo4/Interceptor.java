package com.huaxu.minimybatis.aop.proxy.demo4;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 2:15 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface Interceptor {

    Object intercept(Invocation invocation) throws Throwable;

    default Object wrap(Object target) {
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler(target, this);
        return proxyInvocationHandler.getProxy();
    }

}
