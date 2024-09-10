package com.huaxu.minimybatis.algorithm.dp;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/9/8 11:12
 */
public class CanPartition {
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(new CanPartition().canPartition(nums));
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];

        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
           /* for (int j = 1; j <= target; j++) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }*/
        }
        // 集合中的元素正好可以凑成总和target
        if (dp[target] == target) return true;
        return false;
    }
}
