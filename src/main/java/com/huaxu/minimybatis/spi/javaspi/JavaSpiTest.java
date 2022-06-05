package com.huaxu.minimybatis.spi.javaspi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-06-05 1:08 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class JavaSpiTest {

    public static void main(String[] args) {
        try {
            ServiceLoader<IRepository> serviceLoader = ServiceLoader.load(IRepository.class);
            Iterator<IRepository> it = serviceLoader.iterator();
            while (it != null && it.hasNext()){
                IRepository repository = it.next();
                System.out.println("class:" + repository.getClass().getName());
                repository.save("tom");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
