package com.huaxu.minimybatis.spring.service;

import com.huaxu.minimybatis.spring.event.UserRegisterEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-04-13 11:34 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
@Service
public class UserSerivceImpl implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void register(String userName){
        System.out.println(userName + "  ---   执行注册逻辑");
        applicationEventPublisher.publishEvent(new UserRegisterEvent((ApplicationContext) this, userName));
    }

}
