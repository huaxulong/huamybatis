package com.huaxu.minimybatis.aop.proxy.factory;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

/**
 * @description: MyPointCut
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-28 3:50 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class MyPointCut implements Pointcut {
    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                // 匹配所有的类
                return true;
            }
        };
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new StaticMethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                // 如果方法名称是 go , 则匹配， 否则不匹配。
                if (method.getName().equals("go")) {
                    return true;
                }
                return false;
            }
        };
    }
}
