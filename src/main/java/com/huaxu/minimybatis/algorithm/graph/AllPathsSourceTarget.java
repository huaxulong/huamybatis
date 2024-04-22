package com.huaxu.minimybatis.algorithm.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 图的遍历：leetcode:110
 * @Author: Mr.Hua
 * @date: 2024/4/21 20:10
 */
public class AllPathsSourceTarget {

    // 记录所有路径
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // 维护递归过程中经过的路径
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path);
        return res;
    }

    /* 图的遍历框架 */
    void traverse(int[][] graph, int s, LinkedList<Integer> path) {

        // 添加节点 s 到路径
        path.addLast(s);

        int n = graph.length;
        if (s == n - 1) {
            // 到达终点
            res.add(new LinkedList<>(path));
            // 可以在这直接 return，但要 removeLast 正确维护 path
             path.removeLast();
             return;
            // 不 return 也可以，因为图中不包含环，不会出现无限递归
        }

        // 递归每个相邻节点
        for (int v : graph[s]) {
            traverse(graph, v, path);
        }

        // 从路径移出节点 s
        path.removeLast();
    }

    public static void main(String[] args) {
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        AllPathsSourceTarget target = new AllPathsSourceTarget();
        List<List<Integer>> lists = target.allPathsSourceTarget(graph);
        System.out.println(lists);
    }

}
