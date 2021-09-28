package com.huaxu.minimybatis.aop.proxy.demo3;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 1:42 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class AfterIntercept implements Interceptor{
    @Override
    public void intercept() {
        System.out.println("AfterIntercept : After Hello....");
    }
}
