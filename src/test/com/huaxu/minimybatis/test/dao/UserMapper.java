package com.huaxu.minimybatis.test.dao;

import com.huaxu.minimybatis.test.pojo.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 5:08 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface UserMapper {

    /**
     * 获取单个user
     *
     * @param id
     * @return
     * @see
     */
    User getUser(String id);

    /**
     * 获取所有用户
     *
     * @return
     * @see
     */
    List<User> getAll();

    /**
     * 更新用户（功能未完成）
     *
     * @param id
     */
    void updateUser(String id);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int addUser(User user);


}
