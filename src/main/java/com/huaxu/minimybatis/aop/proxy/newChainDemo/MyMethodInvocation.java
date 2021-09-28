package com.huaxu.minimybatis.aop.proxy.newChainDemo;

/**
 * @description: MyMethodInvocation
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 4:33 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface MyMethodInvocation {

    /**
     * 进入拦截器链中的下一个拦截器 ， 驱动责任链向前运行
     * @return
     * @throws Throwable
     */
    Object proceed() throws Throwable;

}
