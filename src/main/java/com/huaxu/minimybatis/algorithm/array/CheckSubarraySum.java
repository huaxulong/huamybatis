package com.huaxu.minimybatis.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/8/21 22:19
 */
public class CheckSubarraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        if (null == nums || nums.length < 2) {
            return false;
        }
        // 算出前缀和 前缀余数数组
        // 使用hash,key为余数，value为下标,如果key在前缀余数数组中存在，则说明存在子数组
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int temp = sum;
            int mod = temp % k;
            if (map.containsKey(mod)) {
                if (i - map.get(mod) <= 1) {
                    continue;
                }
                return true;
            }
            map.put(mod, i);
        }
        return false;
    }

    public static void main(String[] args) {
        CheckSubarraySum checkSubarraySum = new CheckSubarraySum();

        boolean b = checkSubarraySum.checkSubarraySum(new int[]{1, 0}, 2);
        System.out.println(b);
    }

}
