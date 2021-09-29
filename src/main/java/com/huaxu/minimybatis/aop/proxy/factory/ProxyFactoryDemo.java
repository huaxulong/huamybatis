package com.huaxu.minimybatis.aop.proxy.factory;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-28 4:00 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class ProxyFactoryDemo {

    public static void main(String[] args) {
        Animal catTarget = new Cat();

        // 通过目标对象， 构造 proxyFactory 对象
        ProxyFactory factory = new ProxyFactory(catTarget);

        // 新增一个 Advice(DefaultPointcutAdvisor)   切点～
        Advice advice = new MyMethodInterceptor();
        factory.addAdvice(advice);

        //添加一个 pointcutAdvisor  切面
        Advisor advisor = new MyPointcutAdvisor(advice);
        factory.addAdvisor(advisor);

        Animal catProxy = (Animal) factory.getProxy();

        System.out.println(catProxy.getClass());
        catProxy.eat();

        System.out.println("---------------------->>>-->>>-->>>-------");

        catProxy.go();

    }

    public static class MyMethodInterceptor implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            if (invocation.getMethod().getName().equals("toString")) {
                return null;
            }
            System.out.println("前置通知 处理开始-------------------------------");

            Object ret = invocation.proceed();

            System.out.println("后置通知 处理完成-------------------------------");
            return ret;
        }
    }

}
