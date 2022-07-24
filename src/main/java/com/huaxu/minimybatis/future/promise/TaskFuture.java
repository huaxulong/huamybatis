package com.huaxu.minimybatis.future.promise;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-07-23 6:52 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface TaskFuture extends Future<Object> {

    /**
     * 检查任务是否完成成功
     *
     * @return
     */
    boolean isSuccess();

    /**
     * 获取任务失败原因
     *
     * @return
     */
    Throwable cause();

    /**
     * 如果任务完成，则返回任务结果，如果任务失败或者未完成则直接返回
     *
     * @return
     */
    Object getNow();

    /**
     * 直到任务完成
     *
     * @return
     * @throws InterruptedException
     */
    TaskFuture await() throws InterruptedException;

    /**
     * 最大等待超时时间，如果任务没完成返回 false, 如果完成则返回 true
     *
     * @param timeout
     *            最大等待超时时间
     * @param unit
     *            超时时间单位
     * @return
     * @throws InterruptedException
     */
    boolean await(long timeout, TimeUnit unit) throws InterruptedException;

    /**
     * 任务完成时回调动作
     *
     * @param callback
     *            回调动作
     * @return
     */
    TaskFuture onComplete(TaskCallBack callback);

    /**
     * 任务成功时回调动作
     *
     * @param callback
     *            回调动作
     * @return
     */
    TaskFuture onSuccess(TaskCallBack callback);

    /**
     * 任务失败时回调动作
     *
     * @param callback
     *            回调动作
     * @return
     */
    TaskFuture onFailure(TaskCallBack callback);

    boolean hasAttr(String key);

    Object getAttr(String key);

    TaskFuture addAttr(String key, Object value);

    Object removeAttr(String key);


}
