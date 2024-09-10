package com.huaxu.minimybatis.algorithm.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: leetcode 307
 * @Author: Mr.Hua
 * @date: 2024/8/17 22:20
 */
public class NumArray {

    private int[] nums;

    // 格式 left|right
    private List<String> list = new ArrayList<>();

    private Map<String, Integer> cache = new HashMap<>();

    public NumArray(int[] nums) {
        this.nums = nums;
    }

    public void update(int index, int val) {
        int old = nums[index];
        nums[index] = val;
        for (String key : list) {
            String[] split = key.split("\\|");
            int left = Integer.parseInt(split[0]);
            int right = Integer.parseInt(split[1]);
            if (left <= index && index <= right) {
                cache.put(key, (cache.get(key) - old + val));
            }
        }
    }

    public int sumRange(int left, int right) {
        String key = left + "|" + right;
        if (cache.containsKey(key)) {
            return cache.get(key).intValue();
        }
        int sum = 0;
        while (left <= right) {
            if (left == right) {
                sum += nums[left];
                break;
            }
            sum += nums[left] + nums[right];
            left++;
            right--;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }

}
