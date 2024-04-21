package com.huaxu.minimybatis.algorithm.backtrace;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 子集(leetcode : 90) 元素可重不可复选
 * @Author: Mr.Hua
 * @date: 2024/4/21 16:51
 */
public class SubsetsWithDup {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 先排序，让相同的元素靠在一起
        Arrays.sort(nums);
        backtrack(nums, 0);
        return res;
    }

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    private void backtrack(int[] nums, int index) {
        res.add(new LinkedList<>(track));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        SubsetsWithDup withDup = new SubsetsWithDup();
        List<List<Integer>> lists = withDup.subsetsWithDup(nums);
        System.out.println(lists);
    }

}
