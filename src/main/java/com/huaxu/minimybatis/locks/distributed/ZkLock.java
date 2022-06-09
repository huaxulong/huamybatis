package com.huaxu.minimybatis.locks.distributed;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-06-07 3:11 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class ZkLock {

    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);

        // 创建分布式锁，锁空间的根节点路径为/curator/lock
        InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");

        try {
            long beginMinis = System.currentTimeMillis();
            mutex.acquire();
            long endMinis = System.currentTimeMillis();
            System.out.println("获取锁总共耗时：" + (endMinis - beginMinis));
            System.out.println("我抢到了锁，我要执行10秒钟");
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        client.close();


    }

}