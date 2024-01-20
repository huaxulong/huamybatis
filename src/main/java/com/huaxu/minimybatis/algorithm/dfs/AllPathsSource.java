package com.huaxu.minimybatis.algorithm.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/1/20 11:00
 */
public class AllPathsSource {

    List<List<Integer>> ans;        // 用来存放满足条件的路径
    List<Integer> cnt;        // 用来保存 dfs 过程中的节点值

    /**
     * 所有可能的路径
     *
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ans = new ArrayList<>();
        cnt = new ArrayList<>();
        cnt.add(0);   // 注意，0 号节点要加入 cnt 数组中
        dfs(graph, 0);
        return ans;
    }

    private void dfs(int[][] graph, int node) {
        if (node == graph.length - 1) {  // 如果当前节点是 n - 1，那么就保存这条路径
            ans.add(new ArrayList<>(cnt));
            return;
        }
        for (int index = 0; index < graph[node].length; index++) {
            int nextNode = graph[node][index];
            cnt.add(nextNode);
            dfs(graph, nextNode);
            cnt.remove(cnt.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {{1,2},{3},{3},{}};
        AllPathsSource source = new AllPathsSource();
        List<List<Integer>> lists = source.allPathsSourceTarget(graph);
        System.out.println(lists);
    }

}
