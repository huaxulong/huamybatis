package com.huaxu.minimybatis.test.juc;

import com.huaxu.minimybatis.juc.threadPool.ConsumeScheduleThreadPoolService;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsumeScheduleThreadPoolServiceTest {

    @Test
    public void test() {

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            private AtomicInteger threadIndex = new AtomicInteger(0);

            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread thread = new Thread(r, "ConsumeMessageScheduledThread_" + this.threadIndex.incrementAndGet());
                thread.setDaemon(false);
                return thread;
            }
        });

        ConsumeScheduleThreadPoolService consumeScheduleThreadPoolService = new ConsumeScheduleThreadPoolService(scheduledExecutorService);

        consumeScheduleThreadPoolService.start();

        System.out.println("线程结束111");

        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("线程结束");
    }


}
