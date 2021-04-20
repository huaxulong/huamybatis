package com.huaxu.minimybatis.locks;

public interface BrokingQueue<T> {

    void put(T element) throws InterruptedException;

    T take() throws InterruptedException;

}
