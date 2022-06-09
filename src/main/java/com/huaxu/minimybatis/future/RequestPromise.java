package com.huaxu.minimybatis.future;

import lombok.Data;
import lombok.ToString;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-06-09 4:01 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
@Data
@ToString
public class RequestPromise {

     private UserRequest userRequest;

     private Result result;

    public RequestPromise(UserRequest userRequest) {
        this.userRequest = userRequest;
    }
}
