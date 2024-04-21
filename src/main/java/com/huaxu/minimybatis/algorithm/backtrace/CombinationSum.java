package com.huaxu.minimybatis.algorithm.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: LeetCode:39
 * 子集/组合（元素无重可复选）
 * @Author: Mr.Hua
 * @date: 2024/4/14 20:08
 */
public class CombinationSum {

    List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        Arrays.sort(candidates);
        int target = 7;
        CombinationSum combinationSum = new CombinationSum();
        List<List<Integer>> lists = combinationSum.combinationSum(candidates, target);
        System.out.println(lists);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> route = new ArrayList<>();
        dfs(candidates, target, route, 0);
        return result;
    }

    private void dfs(int[] candidates, int target, List<Integer> route, int index) {
        if (sum(route) > target) {
            return;
        }
        if (sum(route) == target) {
            List<Integer> integerArrayList = new ArrayList<>(route);
            result.add(integerArrayList);
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            route.add(candidates[i]);
            dfs(candidates, target, route, i);
            route.remove(route.size() - 1);
        }
    }

    private int sum(List<Integer> route) {
        if (route != null && route.size() > 0) {
            return route.stream().reduce(Integer::sum).get();
        }
        return 0;
    }
}
