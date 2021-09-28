package com.huaxu.minimybatis.aop.proxy.demo5;

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

    private Object target;

    private String tag;

    public void setTag(String tag){
        this.tag = tag;
    }

    public JdkDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 直接输出 hashCode 值，避免 invoke 递归调用，导致栈溢出
        if (method.getName().equals("hashCode")) {
            return hashCode();
        }
        System.out.println("JdkDynamicProxy1 invoke 方法执行前---------------" + info(proxy));
        Object obj = method.invoke(target, args);
        System.out.println("JdkDynamicProxy1 invoke 方法执行后----------------" + info(proxy));
        return obj;
    }

    /**
     * 输出代理类的一些信息，比如类名，hashCode 等
     * @param proxy
     * @return
     */
    private String info(Object proxy) {
        return proxy.getClass().getName() + ":" + proxy.hashCode() + "----------------" + this.tag;
    }

    /**
     * 获取被代理接口实例对象
     *
     *      通过 Proxy.newProxyInstance 可以获得一个代理对象，它实现了 target.getClass().getInterfaces() 接口
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
