package com.huaxu.minimybatis.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/8/29 23:33
 */
public class FindDisappearedNumbers {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] ints = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ints[nums[i] - 1] = 1;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == 0) {
                list.add(i + 1);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        FindDisappearedNumbers findDisappearedNumbers = new FindDisappearedNumbers();
        List<Integer> disappearedNumbers = findDisappearedNumbers.findDisappearedNumbers(nums);
        System.out.println(disappearedNumbers);
    }

}
