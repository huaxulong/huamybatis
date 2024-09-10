package com.huaxu.minimybatis.array;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/9/7 12:37
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        //ans初始设置为0的目的就是因为
        //一个数与0运算=这个数本身
        //所以ans初始为0不影响结果
        int ans = 0;
        for(int num : nums){
            ans ^= num;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,1,2,3,4};
        int res = new SingleNumber().singleNumber(nums);
        System.out.println(res);
    }

}
