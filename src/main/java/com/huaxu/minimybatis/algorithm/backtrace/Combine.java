package com.huaxu.minimybatis.algorithm.backtrace;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/4/11 23:27
 */
public class Combine {

    /**
     * 典型的元素无重复，不可重复选的类型
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> trace = new LinkedList<>();
        dfs(trace, 1, n, k);
        return result;
    }

    private List<List<Integer>> result = new LinkedList<>();

    private void dfs(List<Integer> trace, int index, int n, int k) {
        if (trace.size() == k) {
            result.add(new LinkedList<>(trace));
        }
        for (int i = index; i <= n; i++) {
            trace.add(i);
            dfs(trace, i + 1, n, k);
            trace.remove(trace.size() - 1);
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
