package com.huaxu.minimybatis.aop.proxy.chainDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: JdkDynamicProxy
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 3:40 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class JdkDynamicProxy implements InvocationHandler {

    /**
     * 被代理对象
     */
    private Object target;

    private AbstractHandler.HeadHandler headHandler;

    public JdkDynamicProxy(Object target, AbstractHandler.HeadHandler headHandler) {
        this.target = target;
        this.headHandler = headHandler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TargetMethod targetMethod = new TargetMethod();
        targetMethod.setTarget(target);
        targetMethod.setMethod(method);
        targetMethod.setArgs(args);
        return headHandler.proceed(targetMethod);
    }

    /**
     * 获取被代理接口实例对象
     * <p>
     * 通过 Proxy.newProxyInstance 可以获得一个代理对象，它实现了 target.getClass().getInterfaces() 接口
     *
     * @param <T>
     * @return
     */
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }
}
