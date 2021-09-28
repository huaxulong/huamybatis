package com.huaxu.minimybatis.aop.proxy.newChainDemo;

import lombok.Data;

import java.util.List;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 4:37 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
@Data
public class MyMethodInvocationImpl implements MyMethodInvocation{

    /**
     * 拦截器链
     */
    private List<MyMethodInterceptor> interceptorList;

    /**
     * 被代理的目标方法
     */
    private TargetMethod targetMethod;

    /**
     * 当前调用的拦截器索引
     */
    private int currentInterceptorIndex = 0;

    public MyMethodInvocationImpl(List<MyMethodInterceptor> interceptorList, TargetMethod targetMethod) {
        this.interceptorList = interceptorList;
        this.targetMethod = targetMethod;
    }

    @Override
    public Object proceed() throws Throwable {
        /**
         * 相等说明拦截器已经执行完了
         */
        if (this.currentInterceptorIndex == this.interceptorList.size()){
            return targetMethod.getMethod().invoke(targetMethod.getTarget(), targetMethod.getArgs());
        }
        // 获取下一个拦截器， 并调用其 invoke() 方法
        MyMethodInterceptor interceptor = this.interceptorList.get(currentInterceptorIndex++);
        return interceptor.invoke(this);
    }
}
