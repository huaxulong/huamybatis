package com.huaxu.minimybatis.algorithm.backtrace;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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

    Queue<Integer> visited = new ArrayDeque<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> route = new ArrayList<>();
        backtrace(nums, route);
        return routes;
    }

    public void backtrace(int[] nums, List<Integer> route) {
        if (route.size() == nums.length) {
            routes.add(new ArrayList<>(route));
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (visited.contains(num)) {
                continue;
            }
            visited.add(nums[i]);
            route.add(nums[i]);
            backtrace(nums, route);
            route.remove(route.size() - 1);
            visited.remove(nums[i]);
        }
    }

}
