package com.huaxu.minimybatis.array;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/1/28 11:29
 */
public class DirSearch {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        DirSearch search = new DirSearch();
        int index = search.search(nums, 9);
        System.out.println(index);
    }

    public int search(int[] nums, int target) {
        if (nums != null || nums.length > 0) {
            int beginIndex = 0;
            int endIndex = nums.length - 1;
            return getIndex(beginIndex, endIndex, target, nums);
        }
        return -1;
    }

    public int getIndex(int begin, int end, int target, int[] nums) {
        if (begin > end || begin < 0 || end < 0) {
            return -1;
        }
        int middle = (begin + end) / 2;
        if (nums[middle] > target) {
            end = middle - 1;
            return getIndex(begin, end, target, nums);
        } else if (nums[middle] < target) {
            begin = middle + 1;
            return getIndex(begin, end, target, nums);
        } else {
            return middle;
        }
    }

}
