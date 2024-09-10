package com.huaxu.minimybatis.algorithm.dp;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/8/25 22:28
 */
public class IntegerBreak {

    public int integerBreak(int n) {
        // 将i 拆分成 j 和 i-j 的和，若 i-j 不再拆分，则乘积为 j*(i-j)
        // 若 i-j 继续拆分，则乘积为 j*dp[i-j]
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
            print(dp);
        }
        return dp[n];
    }

    private void print(int[] dp){
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        IntegerBreak integerBreak = new IntegerBreak();
        int count = integerBreak.integerBreak(60);
        System.out.println(count);
    }

}
