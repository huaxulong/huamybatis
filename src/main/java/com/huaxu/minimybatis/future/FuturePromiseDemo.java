package com.huaxu.minimybatis.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2022-06-09 3:58 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class FuturePromiseDemo {

    // 模拟数据行
    private Integer stock = 6;

    private BlockingQueue<RequestPromise> queue = new LinkedBlockingQueue<>(100);

    /**
     * 启动10个线程，
     * 库存6个,
     * 生成一个合并队列
     * 每个用户能拿到自己的请求响应
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        FuturePromiseDemo futurePromiseDemo = new FuturePromiseDemo();
        futurePromiseDemo.mergeJob();

//        Thread.sleep(2000);

        List<Future<Result>> futureList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            final Long orderId = i + 100L;
            final Long userId = Long.valueOf(i);
            Future<Result> future = executorService.submit(() -> {
                return futurePromiseDemo.oprate(new UserRequest(orderId, userId, 1));
            });
            futureList.add(future);
        }

        futureList.forEach(future -> {
            try {
                Result result = future.get(300, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName() + ":客户端请求响应:" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public Result oprate(UserRequest userRequest) throws InterruptedException {
        // TODO 阈值判断
        // TODO 队列的创建
        RequestPromise requestPromise = new RequestPromise(userRequest);
        synchronized (requestPromise) {
            boolean enqueueSuccess = queue.offer(requestPromise, 100, TimeUnit.MILLISECONDS);
            if (! enqueueSuccess) {
                return new Result(false, "系统繁忙");
            }
            try {
                requestPromise.wait(200);
                if (requestPromise.getResult() == null) {
                    return new Result(false, "等待超时");
                }
            } catch (InterruptedException e) {
                return new Result(false, "被中断");
            }
        }
        return requestPromise.getResult();
    }





    public void mergeJob() {
        new Thread(() -> {
            List<RequestPromise> list = new ArrayList<>();
            while (true){
                if (queue.isEmpty()){
                    try {
                        Thread.sleep(10);
                        continue;
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

                int batchSize = queue.size();

                for (int i = 0; i < batchSize; i++) {
                    try {
                        list.add(queue.poll(10, TimeUnit.MILLISECONDS));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName() + ":合并扣减库存:" + list);

                int sum = list.stream().mapToInt(e -> e.getUserRequest().getCount()).sum();
                // 两种情况
                if (sum <= stock) {
                    stock -= sum;
                    // notify user
                    list.forEach(requestPromise -> {
                        requestPromise.setResult(new Result(true, "ok"));
                        synchronized (requestPromise) {
                            requestPromise.notify();
                        }
                    });
                    list.clear();
                    continue;
                }
                for (RequestPromise requestPromise : list) {
                    int count = requestPromise.getUserRequest().getCount();
                    if (count <= stock) {
                        stock -= count;
                        requestPromise.setResult(new Result(true, "ok"));
                    } else {
                        requestPromise.setResult(new Result(false, "库存不足"));
                    }
                    synchronized (requestPromise) {
                        requestPromise.notify();
                    }
                }
                list.clear();
            }
        }, "mergeThread").start();
    }

}
