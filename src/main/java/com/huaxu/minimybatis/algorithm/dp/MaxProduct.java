package com.huaxu.minimybatis.algorithm.dp;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/8/17 15:48
 */
public class MaxProduct {

    public static void main(String[] args) {
        MaxProduct maxProduct = new MaxProduct();
        int[] nums = {2,3,-2,4};
        int i = maxProduct.maxProduct(nums);
        System.out.println(i);
    }

    public int maxProduct(int[] nums) {
        long maxF = nums[0], minF = nums[0];
        int ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            long mx = maxF, mn = minF;
            long tmpMaxF = Math.max(maxF * nums[i], Math.max(nums[i], minF * nums[i]));
            minF = Math.min(minF * nums[i], Math.min(nums[i], maxF * nums[i]));
        }
        return 1;
    }

}
