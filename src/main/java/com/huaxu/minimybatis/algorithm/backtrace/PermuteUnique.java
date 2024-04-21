package com.huaxu.minimybatis.algorithm.backtrace;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 47. 全排列 II
 * @Author: Mr.Hua
 * @date: 2024/4/21 12:23
 */
public class PermuteUnique {

    List<List<Integer>> result = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        LinkedList<Integer> list = new LinkedList<>();
        // 「路径」中的元素会被标记为 true，避免重复使用
        boolean[] used = new boolean[nums.length];
        backTrace(list, nums, used);
        return result;
    }

    private void backTrace(LinkedList<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            result.add(new LinkedList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 如果 nums[i] == nums[i-1] 则跳过
            if (used[i]) {
                continue;
            }
            // 当出现重复元素时，比如输入 nums = [1,2,2',2'']，2' 只有在 2 已经被使用的情况下才会被选择，
            // 同理，2'' 只有在 2' 已经被使用的情况下才会被选择，这就保证了相同元素在排列中的相对位置保证固定。
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            used[i] = true;
            backTrace(list, nums, used);
            list.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int nums[] = {1, 1, 2};
//        int nums[] = {-1, 2, -1, 2, 1, -1, 2, 1};
        PermuteUnique permuteUnique = new PermuteUnique();
        List<List<Integer>> lists = permuteUnique.permuteUnique(nums);
        System.out.println(lists);
    }

}
