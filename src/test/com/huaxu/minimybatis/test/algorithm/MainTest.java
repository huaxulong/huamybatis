package com.huaxu.minimybatis.test.algorithm;

import com.huaxu.minimybatis.algorithm.tree.TreeNode;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2023/11/18 22:51
 */
public class MainTest {

    @Test
    public void testFiled() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TreeNode root = new TreeNode(1);
        Class<? extends TreeNode> aClass = root.getClass();
        Field field = aClass.getField("val");

        System.out.println(field.getName());
        Object o = field.get(root);
        System.out.println(o);

        Method getMethod = aClass.getMethod("getValue");
        Integer value =(Integer) getMethod.invoke(root);

        System.out.println(field.getName() + "\t" + value);
    }

}
