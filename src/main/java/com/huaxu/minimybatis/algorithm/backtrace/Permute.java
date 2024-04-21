package com.huaxu.minimybatis.algorithm.backtrace;

import java.util.*;

/**
 * @description: 全排列
 * @Author: Mr.Hua
 * @date: 2024/4/14 16:55
 */
public class Permute {

    public static void main(String[] args) {
        Permute permute = new Permute();
        int[] nums = {1, 2, 3};
        List<List<Integer>> permute1 = permute.permute(nums);
        System.out.println(permute1);
    }

    List<List<Integer>> routes = new ArrayList<>();

    /**
     * 全排列：元素无重，不可复选
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> route = new ArrayList<>();

        // 「路径」中的元素会被标记为 true，避免重复使用
        boolean[] used = new boolean[nums.length];

        backtrace(nums, route, used);
        return routes;
    }

    private void backtrace(int[] nums, List<Integer> route, boolean[] used) {
        if (route.size() == nums.length){
            routes.add(new LinkedList<>(route));
        }
        for (int i = 0; i < nums.length; i++) {
            if (route.contains(nums[i])){
                continue;
            }
            if (used[i]){
                continue;
            }
            route.add(nums[i]);
            used[i] = true;
            backtrace(nums, route, used);
            route.remove(route.size() - 1);
            used[i] = false;
        }
    }

}
