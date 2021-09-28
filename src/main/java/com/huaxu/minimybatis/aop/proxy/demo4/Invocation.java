package com.huaxu.minimybatis.aop.proxy.demo4;

import java.lang.reflect.Method;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 2:13 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Invocation {

    private Object target;

    private Method method;

    private Object[] args;

    public Invocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public Object invoke() throws Throwable {
        return method.invoke(target, args);
    }
}
