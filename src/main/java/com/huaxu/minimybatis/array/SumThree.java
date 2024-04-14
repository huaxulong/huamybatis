package com.huaxu.minimybatis.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/3/4 10:56
 */
public class SumThree {

    List<List<Integer>> res = new ArrayList<>();

    public void dp(List<Integer> desc, int[] arr, int startIndex) {
        if (desc.size() > 3){
            return;
        }
        if (desc.size() == 3 && getSum(desc) == 0) {
            ArrayList<Integer> list = new ArrayList<>(desc);
            Collections.sort(list);
            if (!isMatch(res, list)) {
                res.add(list);
            }
            return;
        }
        for (int i = startIndex; i < arr.length; i++) {
            desc.add(arr[i]);
            dp(desc, arr, i + 1);
            desc.remove(desc.size() - 1);
        }
    }

    private boolean isMatch(List<List<Integer>> res, ArrayList<Integer> list) {
        for (int i = 0; i < res.size(); i++) {
            if (isTrue(res.get(i), list)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTrue(List<Integer> list1, ArrayList<Integer> list2) {
        if (list1.size()!= list2.size()){
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i)){
                return false;
            }
        }
        return true;
    }

    private int getSum(List<Integer> desc) {
        int sum = 0;
        for (int i = 0; i < desc.size(); i++) {
            sum += desc.get(i);
        }
        return sum;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<Integer> desc = new ArrayList<>();
        dp(desc, nums, 0);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        SumThree sumThree = new SumThree();
        List<List<Integer>> lists = sumThree.threeSum(arr);
        System.out.println(lists);
    }

}
