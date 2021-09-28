package com.huaxu.minimybatis.aop.proxy.newChainDemo;

/**
 * @description: MyMethodInterceptor
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 4:34 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface MyMethodInterceptor {

    /**
     * 类似于 InvocationHandler 的 invoke 方法， 用于对方法的增强。
     *          并通过 invocation 参数驱动责任链向前运行
     * @param invocation
     * @return
     * @throws Throwable
     */
    Object invoke(MyMethodInvocation invocation) throws Throwable;

}
