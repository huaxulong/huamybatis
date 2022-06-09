package com.huaxu.minimybatis.future;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-06-09 4:02 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
@Data
@ToString
@AllArgsConstructor
public class UserRequest {

    private Long orderId;

    private Long userId;

    private Integer count;


}
