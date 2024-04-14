package com.huaxu.minimybatis.algorithm.backtrace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/4/14 20:08
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        CombinationSum combinationSum = new CombinationSum();
        List<List<Integer>> lists = combinationSum.combinationSum(candidates, target);
        System.out.println(lists);
    }

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> route = new ArrayList<>();
        dfs(candidates, target, route, 0);
        return result;
    }

    private void dfs(int[] candidates, int target, List<Integer> route, int start) {
        if (sum(route) > target) {
            return;
        }
        if (sum(route) == target) {
            List<Integer> integerArrayList = new ArrayList<>(route);
            /*if (contains(integerArrayList)) {
                result.add(integerArrayList);
            }*/
            result.add(integerArrayList);
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            route.add(candidates[i]);
            dfs(candidates, target, route, i);
            route.remove(route.size() - 1);
        }
    }

    /**
     * 能够添加
     *
     * @param route
     * @return
     */
    private boolean contains(List<Integer> route) {
        Collections.sort(route);
        if (result == null || result.size() == 0) {
            return true;
        }
        for (List<Integer> list : result) {
            if (isMatch(list, route)) {
                return false;
            }
        }
        return true;
    }

    private boolean isMatch(List<Integer> route, List<Integer> list) {
        if (list.size() != route.size()) {
            return false;
        }
        return matchLength(list, route) == list.size();
    }

    private int matchLength(List<Integer> route, List<Integer> list) {
        int result = 0;
        for (int i = 0; i < route.size(); i++) {
            if (route.get(i).equals(list.get(i))){
                result++;
            }
        }
        return result;
    }


    private int sum(List<Integer> route) {
        if (route != null && route.size() > 0) {
            return route.stream().reduce(Integer::sum).get();
        }
        return 0;
    }
}
