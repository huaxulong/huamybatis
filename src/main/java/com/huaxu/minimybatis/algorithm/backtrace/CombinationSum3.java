package com.huaxu.minimybatis.algorithm.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: LeetCode:216, 组合总和3
 * @Author: Mr.Hua
 * @date: 2024/4/14 19:13
 */
public class CombinationSum3 {

    public static void main(String[] args) {
        int k = 3, n = 7;
        CombinationSum3 combination = new CombinationSum3();
        List<List<Integer>> lists = combination.combinationSum3(k, n);
        System.out.println(lists);
    }

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> route = new ArrayList<>();
        dfs(route, 1, k, n);
        return result;
    }

    /**
     * 尽量在内层剪枝，外层剪枝效率没有内层高
     *
     * @param route
     * @param start
     * @param k
     * @param n
     */
    private void dfs(List<Integer> route, int start, int k, int n) {
        if (route.size() == k && sum(route) == n) {
            result.add(new ArrayList<>(route));
            return;
        }
        for (int i = start; i < 10; i++) {
            // 深剪枝
            if (sum(route) >= n) {
                break;
            }
            // 将结果放到结果集中
            route.add(i);
            dfs(route, i + 1, k, n);
            route.remove(route.size() - 1);
        }
    }

    private int sum(List<Integer> route) {
        int sum = 0;
        if (route != null && route.size() != 0) {
            for (int i = 0; i < route.size(); i++) {
                sum += route.get(i);
            }
        }
        return sum;
    }

}
