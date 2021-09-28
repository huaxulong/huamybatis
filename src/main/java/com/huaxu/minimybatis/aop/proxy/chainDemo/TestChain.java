package com.huaxu.minimybatis.aop.proxy.chainDemo;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 4:04 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class TestChain {

    public static void main(String[] args) {
        // 1. 构造目标对象
        Cat catTarget = new Cat();

        // 2. 构造 Handler 对象
        AbstractHandler.HeadHandler headHandler = new AbstractHandler.HeadHandler();

        AbstractHandler handler1 = new Handler1();
        headHandler.setNextHandler(handler1);

        Handler2 handler2 = new Handler2();
        handler1.setNextHandler(handler2);

        // 3. 根据目标对象生成代理对象
        JdkDynamicProxy proxy = new JdkDynamicProxy(catTarget, headHandler);

        // JDK 动态代理是基于接口的，所以只能转换为 Cat 实现的接口 Animal
        Animal catProxy = proxy.getProxy();

        // 调用代理对象的方法
        catProxy.eat();
    }

    private static class Handler1 extends AbstractHandler {

        @Override
        Object invoke(TargetMethod targetMethod) throws Throwable {
            System.out.println("Handler1 处理开始-------------------------------");

            Object ret = super.proceed(targetMethod);

            System.out.println("Handler1 处理完成-------------------------------");
            return ret;
        }
    }

    private static class Handler2 extends AbstractHandler {

        @Override
        Object invoke(TargetMethod targetMethod) throws Throwable {
            System.out.println("Handler2 处理开始-------------------------------");

            Object ret = super.proceed(targetMethod);

            System.out.println("Handler2 处理完成-------------------------------");
            return ret;
        }
    }

}
