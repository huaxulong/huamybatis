package com.huaxu.minimybatis.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-05-14 10:34 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
@Data
public class InvokerProtocol implements Serializable {

    private static final long serialVersionUID = 2167810940766509417L;

    //类名
    private String className;

    //方法名
    private String methodName;

    // 参数类型
    private Class<?>[] params;

    // 参数列表
    private Object[] values;
}
