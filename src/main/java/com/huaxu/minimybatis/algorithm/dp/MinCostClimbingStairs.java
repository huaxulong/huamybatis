package com.huaxu.minimybatis.algorithm.dp;

/**
 * @description: leetcode:746. 使用最小花费爬楼梯
 * @Author: Mr.Hua
 * @date: 2024/8/25 19:37
 */
public class MinCostClimbingStairs {

    // 1, 100, 1, 1, 1, 100, 1, 1, 100, 1
    public int minCostClimbingStairs(int[] cost) {
        // 假设到达n级台阶的最小花费为dp[n]，而每次只能爬一级台阶或者两级台阶；
        // f(N) =  min(f(N-1) + cost(N-1), f(N-2) + cost(N-2)))
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i < cost.length + 1; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        MinCostClimbingStairs minCostClimbingStairs = new MinCostClimbingStairs();
        int i = minCostClimbingStairs.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1});
        System.out.println(i);
    }

}
