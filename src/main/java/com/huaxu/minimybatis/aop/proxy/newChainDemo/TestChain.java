package com.huaxu.minimybatis.aop.proxy.newChainDemo;

/**
 * @description: TestChain
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 4:45 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class TestChain {

    public static void main(String[] args) {
        // 1. 构造目标对象
        Cat catTarget = new Cat();

        JdkDynamicProxy jdkDynamicProxy = new JdkDynamicProxy();
        jdkDynamicProxy.setTarget(catTarget);
        jdkDynamicProxy.addMethodInterceptor(new MethodInterceptor1());
        jdkDynamicProxy.addMethodInterceptor(new MethodInterceptor2());

        Animal catProxy = jdkDynamicProxy.getProxy();

        catProxy.eat();

    }

    private static class MethodInterceptor1 implements MyMethodInterceptor {
        @Override
        public Object invoke(MyMethodInvocation invocation) throws Throwable {
            System.out.println("MethodInterceptor1 处理开始-------------------------------");

            Object ret = invocation.proceed();

            System.out.println("MethodInterceptor1 处理完成-------------------------------");
            return ret;
        }
    }

    private static class MethodInterceptor2 implements MyMethodInterceptor {
        @Override
        public Object invoke(MyMethodInvocation invocation) throws Throwable {
            System.out.println("MethodInterceptor2 处理开始-------------------------------");

            Object ret = invocation.proceed();

            System.out.println("MethodInterceptor2 处理完成-------------------------------");
            return ret;
        }
    }

}
