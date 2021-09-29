package com.huaxu.minimybatis.aop.proxy.factory;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-28 3:56 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class MyPointcutAdvisor implements PointcutAdvisor {

    private Pointcut pointcut = new MyPointCut();

    private Advice advice;

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

    public MyPointcutAdvisor(Advice advice) {
        this.advice = advice;
    }
}
