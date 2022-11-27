package com.huaxu.minimybatis.juc.threadPool;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsumeScheduleThreadPoolService {

    private final ScheduledExecutorService cleanExpireMsgExecutors;

    public ConsumeScheduleThreadPoolService(ScheduledExecutorService cleanExpireMsgExecutors) {
        this.cleanExpireMsgExecutors = cleanExpireMsgExecutors;
    }

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public void start() {
        this.cleanExpireMsgExecutors.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (atomicInteger.getAndIncrement() == 2){
                    System.err.println("error");
                    System.out.println(1/0);
                }
                System.out.println("123456");
            }
        }, 5, 5, TimeUnit.SECONDS);
    }
}
