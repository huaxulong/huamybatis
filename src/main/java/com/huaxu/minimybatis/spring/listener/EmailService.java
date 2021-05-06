package com.huaxu.minimybatis.spring.listener;

import com.huaxu.minimybatis.spring.event.UserRegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-04-13 1:35 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
@Service
public class EmailService implements ApplicationListener<UserRegisterEvent> {

    public void onApplicationEvent(UserRegisterEvent event) {
        System.out.println("[onApplicationEvent][给用户("+ event.getUserName() +") 发送邮件]");
    }
}
