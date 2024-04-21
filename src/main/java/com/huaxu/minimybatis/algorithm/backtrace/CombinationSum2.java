package com.huaxu.minimybatis.algorithm.backtrace;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 组合问题和 自集问题是等价的
 * @Author: Mr.Hua
 * @date: 2024/4/21 17:21
 */
public class CombinationSum2 {

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    int sum = 0;

    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        // 先排序，让相同的元素靠在一起
        Arrays.sort(nums);
        backtrack(nums, 0, target);
        return res;
    }

    private void backtrack(int[] nums, int index, int target) {
        // 加一个检枝逻辑
        if (sum > target) {
            return;
        }
        if (sum == target) {
            res.add(new LinkedList<>(track));
        }
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            track.add(nums[i]);
            sum += nums[i];

            backtrack(nums, i + 1, target);
            Integer integer = track.removeLast();
            sum = sum - integer;
        }
    }

    public static void main(String[] args) {
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        CombinationSum2 withDup = new CombinationSum2();
        List<List<Integer>> lists = withDup.combinationSum2(nums, target);
        System.out.println(lists);
    }

}
