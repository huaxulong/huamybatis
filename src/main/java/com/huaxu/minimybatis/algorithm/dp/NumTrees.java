package com.huaxu.minimybatis.algorithm.dp;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/9/7 16:49
 */
public class NumTrees {

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i ; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        NumTrees numTrees = new NumTrees();
        int res = numTrees.numTrees(3);
        System.out.println(res);
    }

}
