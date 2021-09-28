package com.huaxu.minimybatis.aop.proxy.newChainDemo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Method;

/**
 * @description: TargetMethod
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 4:07 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
@Setter
@Getter
@ToString
public class TargetMethod {

    private Object target;

    private Method method;

    private Object[] args;

}
