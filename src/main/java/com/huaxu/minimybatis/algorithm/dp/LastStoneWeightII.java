package com.huaxu.minimybatis.algorithm.dp;

/**
 * @description: Leetcode：1049. 最后一块石头的重量 II
 * @Author: Mr.Hua
 * @date: 2024/9/8 14:13
 */
public class LastStoneWeightII {

    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int stone : stones) {
            for (int i = target; i >= stone; i--) {
                dp[i] = Math.max(dp[i], dp[i - stone] + stone);
            }
        }
        return sum - 2 * dp[target];
    }

    public static void main(String[] args) {
        LastStoneWeightII lastStoneWeightII = new LastStoneWeightII();
        int[] stones = {2, 7, 4, 1, 8, 1};
        System.out.println(lastStoneWeightII.lastStoneWeightII(stones));
    }
}
