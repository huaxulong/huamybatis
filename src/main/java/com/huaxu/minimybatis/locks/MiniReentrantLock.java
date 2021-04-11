package com.huaxu.minimybatis.locks;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-04-11 4:14 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class MiniReentrantLock implements Lock {

    /**
     * 0 : 表示尚未加锁 状态
     * >0 : 表示当前lock 是加锁状态
     */
    private volatile int state;

    /**
     * 独占模式：
     * 当前占用锁的线程
     */
    private Thread exclusiveOwnerThread;

    public Thread getExclusiveOwnerThread() {
        return this.exclusiveOwnerThread;
    }

    /**
     * 队列头节点,
     * 比较特殊： head节点对应的线程就是当前占用锁的线程
     */
    private Node head;

    /**
     * 队列尾节点
     */
    private Node tail;

    /**
     * lock的过程是怎样的？
     * 1： 线程进来后发现， 当前state == 0 ， 这个时候很幸运，直接抢锁
     * 2： 线程进来后发现， 当前state > 0 , 这个时候就需要将当前线程入队
     */
    @Override
    public void lock() {
        acquire(1);
    }

    @Override
    public void unlock() {
        release(1);
    }

    private void release(int arg) {
        if (tryRelease(arg)) {
            Node head = this.head;
            if (head.next != null) {
                // 公平锁 ， 就是唤醒 head.next == null , 说明没有等待者 ， head.next != null 说明有等待者
                unparkSuccessor(head);
            }
        }
    }

    private void unparkSuccessor(Node node) {
        Node s = node.next;
        if (s != null && s.thread != null) {
            System.out.println("Thread Name : " + s.thread.getName() + " 唤醒 ！！！ ");
            LockSupport.unpark(s.thread);
        }
    }

    private boolean tryRelease(int arg) {
        int c = getState() - arg;
        if (getExclusiveOwnerThread() != Thread.currentThread()) {
            throw new RuntimeException("thread must getLock");
        }
        if (c == 0) {
            this.exclusiveOwnerThread = null;
            this.state = c;
            return true;
        }
        this.state = c;
        return false;
    }

    /**
     * 竞争资源
     * 1。尝试获取锁，成功则占用锁且返回..
     * 2. 抢占锁失败， 阻塞当前线程
     *
     * @param arg
     */
    private void acquire(int arg) {
        if (!tryAcquire(arg)) {
            // 添加队列
            Node node = addWaiter();
            acquireQueued(node, arg);
        }
    }

    /**
     * 尝试获取锁失败， 需要做什么呢？
     * 1. 需要将当前线程封装成 node , 加入到阻塞队列
     * 2. 需要将当前线程park 掉， 使线程处于 挂起 状态
     * <p>
     * 唤醒后呢？
     * 1. 检查当前node节点， 是否为 head.next 节点
     * 2. 抢占
     * 成功： 1。 将当前node 设置为 head ， 将老的head 出队操作，返回到业务层面
     * 失败： 2。 继续park ，等待被唤醒
     *
     * @param node
     * @param arg
     */
    private void acquireQueued(Node node, int arg) {
        //只有当前node 成功获取到锁，以后才会跳出自旋
        for (; ; ) {
            // 只有当前node 是 head 的后继节点， 才有这个权限  / head 节点就是当前持有锁的线程
            Node pred = node.prev;
            if (pred == head && tryAcquire(arg)) {
                setHead(node);
                pred.next = null;
                return;
            }

            System.out.println("" + Thread.currentThread().getName() + "， 挂起！");
            LockSupport.park();
            System.out.println("" + Thread.currentThread().getName() + " ， 唤醒！");
            //  ??? 什么时候唤醒被 park的线程呢？ unlock 的时候去 LockSupport.unpark() 线程
        }
    }

    private void setHead(Node node) {
        this.head = node;
        node.thread = null;
        node.prev = null;
    }

    private Node addWaiter() {
        Node newNode = new Node(Thread.currentThread());
        // 如何入队
        /** 1， 找到 newNode 的前置节点 pred
         *  2， 更新 newNode.prev = pred
         *
         */
        Node pred = tail;
        if (pred != null) {
            newNode.prev = pred;
            // 条件成立， 说明当前线程成功入队
            if (compareAndSetTail(pred, newNode)) {
                pred.next = newNode;
                return newNode;
            }
        }
        enq(newNode);
        return newNode;
    }

    /**
     * 自旋入队 ， 只有成功后才返回
     *
     * @param node
     */
    private void enq(Node node) {
        for (; ; ) {
            // 第一种情况： 队列是空队列
            if (tail == null) {
                // 条件成立：说明当前线程 给当前持有锁的线程 补充 head 操作成功了..
                if (compareAndSetHead(new Node())) {
                    tail = head;
                    // 注意： 并没有直接返回， 还会继续自旋...
                }
            } else {
                // 说明当前队列中已经有node了， 这里是一个追加node的过程
                Node pred = tail;
                if (pred != null) {
                    node.prev = pred;
                    if (compareAndSetTail(pred, node)) {
                        pred.next = node;
                        // 注意： 入队成功之后， 一定要return
                        return;
                    }
                }
            }
        }
    }


    private boolean tryAcquire(int arg) {
        if (state == 0) {
            //当state == 0 时， 是否可以直接抢占锁呢？
            //不可以， 如果是非公平锁（可以）， 如果是非公平锁， 讲究先来后到
            // 条件一： !hasQueuePredecessor() 取反之后为true， 表示当前线程前面没有等待着线程
            // 条件二： compareAndSetState(0, arg) 为什么使用CAS ？ 因为lock方法可能有多线程调用的情况。   成立： 说明当前线程抢占锁成功
            if (!hasQueuePredecessor() && compareAndSetState(0, arg)) {
                // 需要将 exclusiveOwnerThread 设置成当前线程
                this.exclusiveOwnerThread = Thread.currentThread();
                return true;
            }
        } else if (Thread.currentThread() == this.exclusiveOwnerThread) {
            int c = getState();
            c = c + arg;

            this.state = c;
            return true;
        }
        return false;
    }

    public int getState() {
        return state;
    }

    /**
     * true 表示当前线程前面有等待者线程
     * 什么时候返回false呢？
     * 1. 当前队列是空
     * 2. 当前线程为head.next 节点线程，  head.next 在任何时候都有权利取争取一下lock
     *
     * @return
     */
    private boolean hasQueuePredecessor() {
        Node h = head;
        Node t = tail;
        Node s;
        /**
         * 条件一 ： h != t
         *          说明当前队列已经有node了，
         * 条件二 ： (s = h.next) == null
         *          已经有 head.next 节点了，其他线程再来这时， 需要返回 true
         *
         * 条件三：  主要说明 lock 的可重入性
         *
         */
        return h != t && ((s = h.next) == null || s.thread != Thread.currentThread());
    }


    static final class Node {
        Node prev;
        Node next;
        /**
         * 封装的线程本身
         */
        Thread thread;

        public Node(Thread thread) {
            this.thread = thread;
        }

        public Node() {
        }
    }


    private static final Unsafe unsafe;
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);

            stateOffset = unsafe.objectFieldOffset
                    (MiniReentrantLock.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset
                    (MiniReentrantLock.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset
                    (MiniReentrantLock.class.getDeclaredField("tail"));

        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private boolean compareAndSetHead(Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    private boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    private boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

}
