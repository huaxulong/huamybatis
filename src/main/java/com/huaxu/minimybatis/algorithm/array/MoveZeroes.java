package com.huaxu.minimybatis.algorithm.array;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/8/29 23:38
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        if (nums.length <= 1){
            return;
        }
        int left = 0;
        int right = 1;
        while (right < nums.length) {
            // 左边的等于0，右边不等于0，交换位置，left++，right++
            // 左边不等于0，右边等于0，left++,right++
            // 左右两边都等于0，right++
            // 左右两边都不等于0，left++，right++
            if (nums[left] == 0 && nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right++;
            } else if (nums[left] != 0 && nums[right] == 0) {
                left++;
                right++;
            } else if (nums[left] == 0 && nums[right] == 0) {
                right++;
            } else {
                left++;
                right++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        new MoveZeroes().moveZeroes(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

}
