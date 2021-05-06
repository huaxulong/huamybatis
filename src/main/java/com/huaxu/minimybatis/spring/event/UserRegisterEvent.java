package com.huaxu.minimybatis.spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-04-13 11:10 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class UserRegisterEvent extends ApplicationContextEvent {
    private static final long serialVersionUID = -102327953357709614L;

    private String userName;

    /**
     * Create a new ContextStartedEvent.
     *
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public UserRegisterEvent(ApplicationContext source) {
        super(source);
    }

    public UserRegisterEvent(ApplicationContext source, String userName){
        super(source);
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

}
