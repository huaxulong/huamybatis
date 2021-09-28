package com.huaxu.minimybatis.aop.proxy.chainDemo;

import lombok.Setter;

/**
 * @description: AbstractHandler
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 3:55 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public abstract class AbstractHandler {

    /**
     * 责任链中下一个处理者
     */
    @Setter
    private AbstractHandler nextHandler;

    /**
     * 是否有下一个处理者
     * @return
     */
    public boolean hasNextHandler() {
        return this.nextHandler != null;
    }

    abstract Object invoke(TargetMethod targetMethod) throws Throwable;

    public final Object proceed(TargetMethod targetMethod) throws Throwable{
        // 如果没有下一个处理者，则直接调用目标对象的被代理方法
        if (!hasNextHandler()) {
            return targetMethod.getMethod().invoke(targetMethod.getTarget(), targetMethod.getArgs());
        }
        return this.nextHandler.invoke(targetMethod);
    }

    /**
     * 第一个 Handler， 不做额外处理， 起驱动责任链向前调用的作用
     */
    public static class HeadHandler extends AbstractHandler {
        @Override
        Object invoke(TargetMethod targetMethod) throws Throwable {
            return null;
        }
    }

}
