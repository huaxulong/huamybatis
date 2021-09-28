package com.huaxu.minimybatis.aop.proxy.demo3;

import java.util.Arrays;
import java.util.List;

/**
 * @description: Client
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 1:51 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Client {

    public static void main(String[] args) {
        Animal cat = new Cat();

        Animal dog = new Dog();

        List<Interceptor> beforeIntercepts = Arrays.asList(new BeforeIntercept());

        List<Interceptor> afterIntercepts = Arrays.asList(new AfterIntercept());

        ProxyInvocation proxyInvocation = new ProxyInvocation(dog, beforeIntercepts, afterIntercepts);

        Animal proxyDog = proxyInvocation.getProxyObject();

        proxyDog.eat();
    }

}
