package com.huaxu.minimybatis.algorithm.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/4/11 23:27
 */
public class Combine {

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> target = new ArrayList<>();
        backtrace(1, target, k, n);
        return list;
    }

    private List<List<Integer>> list = new ArrayList<>();

    private void backtrace(int start, List<Integer> target, int k, int n) {
        if (target.size() == k) {
            list.add(new ArrayList<>(target));
            return;
        }
        for (int i = start ; i <= n; i++) {
            target.add(i);
            backtrace(i + 1, target, k, n);
            target.remove(target.size() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        Combine combine = new Combine();
        List<List<Integer>> list = combine.combine(n, k);
        System.out.println(list);
    }

}
