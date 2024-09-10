package com.huaxu.minimybatis.algorithm.array;

import java.util.*;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/8/19 23:18
 */
public class MedianSlidingWindow {

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        double[] doubles = new MedianSlidingWindow().medianSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(doubles));
    }

    // 小顶堆
    private PriorityQueue<Integer> small = new PriorityQueue<>();

    // 大顶堆
    private PriorityQueue<Integer> large = new PriorityQueue<>((a, b) -> b - a);

    private Map<Integer, Integer> delayed = new HashMap<>();

    private int k = 0;

    // small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
    private int smallSize, largeSize;

    public double[] medianSlidingWindow(int[] nums, int k) {
        // 寻找中位数，维护两个优先队列，一个最大堆，一个最小堆
        // insert 函数而言，他们各自的堆顶元素的平均值即为中位数，如果small堆顶的元素多一个时，small的堆顶元素即为中位数。
        this.k = k;
        int length = nums.length;
        for (int i = 0; i < k; ++i) {
            insert(nums[i]);
        }
        double[] ans = new double[nums.length - k + 1];
        ans[0] = getMedian();
        for (int i = k; i < nums.length; ++i) {
            insert(nums[i]);
            erase(nums[i - k]);
            ans[i - k + 1] = getMedian();
        }
        return ans;
    }

    private void insert(int num) {
        // 如果当前两个优先队列都为空，那么格局元素个数的要求，我们必须将这个元素加入到small中，small的堆顶元素即为中位数。 如果samll非空，我们就可以将num与small的堆顶元素top比较
        // 如果 num <= top ，我们就将其加入 small中；
        // 如果 num > top ，我们就将其加入 large中；
        if (small.isEmpty() || num <= small.peek()) {
            small.offer(num);
            ++smallSize;
        } else {
            large.offer(num);
            ++largeSize;
        }
        makeBalance();
    }

    private void erase(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= small.peek()) {
            --smallSize;
            if (num == small.peek()) {
                prune(small);
            }
        } else {
            --largeSize;
            if (num == large.peek()) {
                prune(large);
            }
        }
        makeBalance();
    }

    private double getMedian() {
        return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
    }

    private void makeBalance() {
        if (smallSize > largeSize + 1) {
            // small 比 large 元素多 2 个
            large.offer(small.poll());
            --smallSize;
            ++largeSize;
            // small 堆顶元素被移除，需要进行 prune
            prune(small);
        } else if (smallSize < largeSize) {
            // large 比 small 元素多 1 个
            small.offer(large.poll());
            ++smallSize;
            --largeSize;
            // large 堆顶元素被移除，需要进行 prune
            prune(large);
        }
    }

    // 不断地弹出 heap 的堆顶元素，并且更新哈希表
    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int num = heap.peek();
            if (delayed.containsKey(num)) {
                delayed.put(num, delayed.get(num) - 1);
                if (delayed.get(num) == 0) {
                    delayed.remove(num);
                }
                heap.poll();
            } else {
                break;
            }
        }
    }

}
