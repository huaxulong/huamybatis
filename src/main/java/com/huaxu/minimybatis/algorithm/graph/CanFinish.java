package com.huaxu.minimybatis.algorithm.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 课程表
 * @Author: Mr.Hua
 * @date: 2024/4/21 21:26
 */
public class CanFinish {

    // 记录一次 traverse 递归经过的节点
    private boolean[] onPath;
    // 记录遍历过的节点，防止走回头路
    private boolean[] visited;
    // 记录图中是否有环
    private boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            // 遍历图中的所有节点
            traverse(graph, i);
        }
        // 只要没有循环依赖可以完成所有课程
        return !hasCycle;
    }

    private void traverse(List<Integer>[] graph, int s) {
        // 出现环
        if (onPath[s]) {
            hasCycle = true;
        }
        if (visited[s] || hasCycle) {
            // 如果已经找到了环，也不用再遍历了
            return;
        }
        // 前序遍历代码位置
        visited[s] = true;
        onPath[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        // 后序遍历代码位置
        onPath[s] = false;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // 图中共有 numCourses 个节点
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            // 修完课程 from 才能修课程 to
            // 在图中添加一条从 from 指向 to 的有向边
            graph[from].add(to);
        }
        return graph;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        CanFinish canFinish = new CanFinish();
        boolean flag = canFinish.canFinish(numCourses, prerequisites);
        System.out.println(flag);
    }

}
