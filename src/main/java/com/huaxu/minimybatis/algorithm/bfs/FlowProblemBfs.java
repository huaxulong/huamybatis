package com.huaxu.minimybatis.algorithm.bfs;

import java.util.List;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/1/20 22:00
 */
public class FlowProblemBfs {

    public static void main(String[] args) {
        int[][] heights = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        FlowProblemBfs bfs = new FlowProblemBfs();
        List<List<Integer>> lists = bfs.pacificAtlantic(heights);
        System.out.println(lists);
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        return null;
    }

}
