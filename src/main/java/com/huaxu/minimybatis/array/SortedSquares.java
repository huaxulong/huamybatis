package com.huaxu.minimybatis.array;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/1/28 14:10
 */
public class SortedSquares {

    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 10};
        int[] nums1 = {-7, -3, 2, 3, 11};
        SortedSquares sortedSquares = new SortedSquares();
        int[] arr = sortedSquares.sortedSquares(nums1);
        System.out.println(arr);
    }

    public int[] sortedSquares(int[] nums) {
        // 定义了一个单调递增的数组，利用双指针法
        int begin = 0;
        int end = nums.length - 1;
        int[] arr = new int[nums.length];
        int index = nums.length - 1;
        while (begin <= end) {
            if (Math.abs(nums[begin]) >= Math.abs(nums[end])) {
                arr[index] = nums[begin] * nums[begin];
                begin++;
            } else {
                arr[index] = nums[end] * nums[end];
                end--;
            }
            index--;
        }
        return arr;
    }

}
