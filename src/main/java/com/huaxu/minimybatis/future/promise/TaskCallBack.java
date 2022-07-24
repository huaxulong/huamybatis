package com.huaxu.minimybatis.future.promise;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-07-23 6:55 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public abstract class TaskCallBack {

    /**
     * 执行动作
     *
     * @param f
     * @return
     */
    public abstract TaskFuture apply(TaskFuture f);


    public TaskCallBack compose(TaskCallBack before) {
        return new TaskCallBack() {
            @Override
            public TaskFuture apply(TaskFuture f) {
                return TaskCallBack.this.apply(before.apply(f));
            }
        };
    }

    public TaskCallBack andThen(TaskCallBack after){
        return new TaskCallBack() {
            @Override
            public TaskFuture apply(TaskFuture f) {
                return after.apply(TaskCallBack.this.apply(f));
            }
        };
    }

}
