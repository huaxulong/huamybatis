package com.huaxu.minimybatis.aop.proxy.demo4;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-27 2:33 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class HelloInvocationDemo {

    public static void main(String[] args) {
        Animal target = new Cat();
        target = (Animal) new LogIntercept().wrap(target);
        target = (Animal) new LogIntercept().wrap(target);

        target.eat();
    }

}
