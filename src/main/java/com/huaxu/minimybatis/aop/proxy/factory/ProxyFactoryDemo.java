package com.huaxu.minimybatis.aop.proxy.factory;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;


/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 5:44 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class ProxyFactoryDemo {

    public static void main(String[] args) {
        // 1. 构造目标对象
        Cat catTarget = new Cat();

        // 2. 通过目标对象，构造 ProxyFactory 对象
        ProxyFactory factory = new ProxyFactory(catTarget);

        // 3. 添加一个方法拦截器
        factory.addAdvice(new MyMethodInterceptorImpl());

        //
        Animal cat = (Animal) factory.getProxy();

        cat.eat();


    }

    public static class MyMethodInterceptorImpl implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            System.out.println("MyMethodInterceptor invoke 调用 before invocation.proceed");

            Object ret = methodInvocation.proceed();

            System.out.println("MyMethodInterceptor invoke 调用 after invocation.proceed");
            return ret;
        }

        @Override
        public String toString() {
            return "MyMethodInterceptorImpl{}";
        }
    }

}
