package com.huaxu.minimybatis.juc.lock.reentranlock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-06 7:19 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class ReentrantlockStore implements StoreInterface{

    private LinkedList linkList = new LinkedList();

    private static int Max_Value = 100;
    private int number;
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();


    public void produce(int number){
        reentrantLock.lock();
        try{
            while(linkList.size()+number>Max_Value){
                System.out.println("【要生产的产品数量】:" + number + "\t【库存量】:"+ linkList.size() + "\t暂时不能执行生产任务!");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
        for (int i=0;i<number;i++ ){
            linkList.add(new Object());
        }

        System.out.println("【已经生产产品数】:" + number + "\t【现仓储量为】:" + linkList.size());
        condition.signalAll();
    }

    public void consume(int number){
        reentrantLock.lock();
        try{
            while (linkList.size()<number){
                System.out.println("【要消费的产品数量】:" + number + "\t【库存量】:"+ linkList.size() + "\t暂时不能执行消费任务!");
                try {
                    condition.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }

        for (int i=0;i<number;i++ ){
            linkList.remove();
        }

        System.out.println("【已经消费产品数】:" + number + "\t【现仓储量为】:" + linkList.size());

        condition.signalAll();
    }

}
