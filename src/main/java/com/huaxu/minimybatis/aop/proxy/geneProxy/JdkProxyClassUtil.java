package com.huaxu.minimybatis.aop.proxy.geneProxy;

import com.huaxu.minimybatis.aop.proxy.demo1.Animal;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-28 2:42 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class JdkProxyClassUtil {

    public static void writeClassToDisk(String path) {
        byte[] classFile = ProxyGenerator.generateProxyClass("$proxy0", new Class[]{Animal.class});
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            fos.write(classFile);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        JdkProxyClassUtil.writeClassToDisk("/Users/dongxuhua/workspace/stjk/proxy/$proxy0.java");

        System.out.println("game over");
    }

}
