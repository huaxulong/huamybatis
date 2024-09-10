package com.huaxu.minimybatis.unionfind;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: 哈希表法：最长子序列长度
 * @Author: Mr.Hua
 * @date: 2024/9/7 11:30
 */
public class LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int max = 1;
        for (int num : nums) {
            int length = 1;
            if (!set.contains(num - 1)) {
                while (set.contains(num + 1)) {
                    num++;
                    length++;
                }
            }
            max = Math.max(max, length);
        }
        return max;
    }

    public static void main(String[] args) {
        LongestConsecutive longestConsecutive = new LongestConsecutive();
        int length = longestConsecutive.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        System.out.println(length);
    }

}
