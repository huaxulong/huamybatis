package com.huaxu.minimybatis.algorithm.backtrace;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 回溯算法，求所有的子集
 * @Author: Mr.Hua
 * @date: 2024/4/21 13:58
 */
public class Subset {

    List<List<Integer>> res = new LinkedList<>();
    // 记录回溯算法的递归路径
    LinkedList<Integer> track = new LinkedList<>();

    /**
     * 假如数组 【1，2，3】=》 所有的子集 则有 【1， 2， 3，12，13，23， 123】
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    private void backtrack(int[] nums, int index) {
        // 搜集路径
        res.add(new LinkedList<>(track));

        for (int i = index; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        Subset subset = new Subset();
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = subset.subsets(nums);
        System.out.println(subsets);
    }

}
