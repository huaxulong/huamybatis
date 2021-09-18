package com.huaxu.minimybatis.juc;

import java.util.Properties;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-28 5:04 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class PropertiesTest {

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        properties.list(System.out);
    }

}
