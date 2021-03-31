package com.huaxu.minimybatis.test;

import com.huaxu.minimybatis.session.SqlSession;
import com.huaxu.minimybatis.session.SqlSessionFactory;
import com.huaxu.minimybatis.session.SqlSessionFactoryBuilder;
import com.huaxu.minimybatis.test.dao.UserMapper;
import com.huaxu.minimybatis.test.pojo.User;

import java.util.List;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-28 4:42 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class Testjdbc {

    public static void main(String[] args) {
        System.out.println(111);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build("com/huaxu/minimybatis/test/conf.properties");
        SqlSession session = factory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println("select");
        List<User> list = mapper.getAll();
        System.out.println(list);

        System.out.println("insert");
        User user = new User();
        user.setName("好好向上");
        int cnt = mapper.addUser(user);
        System.out.println(cnt);


    }

}
