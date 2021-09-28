package com.huaxu.minimybatis.aop.proxy.demo2;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description: CglibDynamicProxy
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 10:51 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class CglibDynamicProxy implements MethodInterceptor {

    /**
     * 目标对象（也被称为被代理对象）
     */
    private Object target;

    public CglibDynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * 获取被代理接口实例对象
     *
     * 通过 enhancer.create 可以获得一个代理对象，它继承了 target.getClass() 类
     *
     * @param <T>
     * @return
     */
    public <T> T getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("CglibDynamicProxy intercept 方法执行前-------------------------------");

        System.out.println("obj = " + obj.getClass());
        System.out.println("method = " + method);
        System.out.println("proxy = " + methodProxy);

        Object object = methodProxy.invoke(target, args);
        System.out.println("CglibDynamicProxy intercept 方法执行后-------------------------------");
        return object;
    }
}
