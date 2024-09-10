package com.huaxu.minimybatis.algorithm.dp;

/**
 * @description: 62：不同路径
 * @Author: Mr.Hua
 * @date: 2024/8/25 20:16
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        // dp[i][j] = dp[i-1][j] + dp[i][j-1]
        int[][] dp = new int[m][n];
        // 初始化dp数组
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int i = new UniquePaths().uniquePaths(3, 7);
        System.out.println(i);
    }

}
