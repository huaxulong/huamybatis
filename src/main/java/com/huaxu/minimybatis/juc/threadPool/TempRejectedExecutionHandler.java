package com.huaxu.minimybatis.juc.threadPool;

/**
 * @description: RejectedExecutionHandler
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-18 4:15 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface TempRejectedExecutionHandler {

    void rejectedExecution(Runnable r, ThreadPoolExecutor executor);

}
