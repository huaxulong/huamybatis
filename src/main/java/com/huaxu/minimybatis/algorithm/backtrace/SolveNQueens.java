package com.huaxu.minimybatis.algorithm.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: N 皇后问题
 * @Author: Mr.Hua
 * @date: 2024/4/14 17:39
 */
public class SolveNQueens {

    List<List<String>> result = new ArrayList<>();

    public static void main(String[] args) {
        SolveNQueens solveNQueens = new SolveNQueens();
        List<List<String>> lists = solveNQueens.solveNQueens(4);
        System.out.println(lists);
    }

    public List<List<String>> solveNQueens(int n) {
        return null;
    }

    public void backtrace(List<String> trace, int n) {
        if (result.size() < n){
            result.add(trace);
        }

        for (int i = 0; i < n; i++) {

        }
    }

    private boolean isMatch(List<List<String>> result, String match) {

        return false;
    }

}
