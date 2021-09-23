package com.huaxu.minimybatis.test.aqs.semaphore;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-23 3:37 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class SemaphoreTest {

    @Test
    public void test3()throws InterruptedException{
        final Semaphore semaphore = new Semaphore(2,true);
        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("线程A获取通行证成功！");
                TimeUnit.SECONDS.sleep(10);
            }catch (Exception e){

            }finally {
                semaphore.release();
            }
        }).start();

        TimeUnit.MICROSECONDS.sleep(200);

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("线程B获取通行证成功！");
                TimeUnit.SECONDS.sleep(10);
            }catch (Exception e){

            }finally {
                semaphore.release();
            }
        }).start();

        TimeUnit.MILLISECONDS.sleep(200);

        new Thread(() ->{
            try {
                semaphore.acquire();
                System.out.println("线程C获取通行证成功!");
            } catch (InterruptedException e) {
            }finally {
                semaphore.release();
            }
        }).start();

        while (true){}
    }

}
