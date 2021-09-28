package com.huaxu.minimybatis.aop.proxy.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 10:23 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class JdkDynamicProxy implements InvocationHandler {

    /**
     * 被代理对象
     */
    private Object target;

    public JdkDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("toString".equals(method.getName())) {
            return null;
        }
        System.out.println("JdkDynamicProxy invoke 方法执行前-------------------------------" + method.getName());
        Object object = method.invoke(target, args);
        System.out.println("JdkDynamicProxy invoke 方法执行后-------------------------------" + "调入结束 " + target.getClass());
        return object;
    }

    /**
     * 获取被代理接口实例对象
     * <p>
     * 通过 Proxy.newProxyInstance 可以获得一个代理对象，它实现了 target.getClass().getInterfaces() 接口
     *
     * @return
     */
    public<T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

}
