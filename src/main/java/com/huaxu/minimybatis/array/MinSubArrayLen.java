package com.huaxu.minimybatis.array;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/1/28 15:26
 */
public class MinSubArrayLen {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int[] nums1 = {1, 1, 1, 1, 1, 1, 1, 1};
        int target = 7;
        int target1 = 11;
        MinSubArrayLen minSubArrayLen = new MinSubArrayLen();
        int res = minSubArrayLen.minSubArrayLen(target, nums);
        System.out.println(res);
    }

    /**
     * 因为都是正整数，所以可以使用双指针的方法
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int result = Integer.MAX_VALUE;
        int begin = 0;
        int end = 0;
        int sum = nums[0];
        while (end < nums.length) {
            if (sum >= target) {
                result = Math.min(result, end - begin + 1);
                sum = sum - nums[begin];
                begin++;
            } else {
                end++;
                if (end < nums.length) {
                    sum = sum + nums[end];
                }
            }
        }
        return Integer.MAX_VALUE == result ? 0 : result;
    }

}
