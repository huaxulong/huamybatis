package com.huaxu.minimybatis.aop.proxy.newChainDemo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: JdkDynamicProxy
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 4:48 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
@NoArgsConstructor
@Data
public class JdkDynamicProxy implements InvocationHandler {

    /**
     * 目标对象（也被称为被代理对象）
     */
    private Object target;

    /**
     * 拦截器链
     */
    private List<MyMethodInterceptor> interceptorList = new ArrayList<>();

    public void addMethodInterceptor(MyMethodInterceptor interceptor) {
        this.interceptorList.add(interceptor);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("toString")) {
            return null;
        }
        TargetMethod targetMethod = new TargetMethod();
        targetMethod.setTarget(target);
        targetMethod.setMethod(method);
        targetMethod.setArgs(args);

        MyMethodInvocation methodInvocation = new MyMethodInvocationImpl(interceptorList, targetMethod);
        return methodInvocation.proceed();
    }

    public <T> T getProxy() {
        return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
