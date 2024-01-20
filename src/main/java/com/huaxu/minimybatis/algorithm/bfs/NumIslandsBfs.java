package com.huaxu.minimybatis.algorithm.bfs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/1/20 16:54
 */
public class NumIslandsBfs {

    boolean[][] visited;
    int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numIslands(char[][] grid) {
        int res = 0;
        visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    bfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    //将这片岛屿上的所有陆地都访问到
    public void bfs(char[][] grid, int x, int y) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int m = cur[0];
            int n = cur[1];
            for (int i = 0; i < 4; i++) {
                int nextx = m + move[i][0];
                int nexty = n + move[i][1];
                if (nexty < 0 || nextx == grid.length || nextx < 0 || nexty == grid[0].length) {
                    continue;
                }
                if (!visited[nextx][nexty] && grid[nextx][nexty] == '1') {
                    queue.offer(new int[]{nextx, nexty});
                    // 只要加入过队列就标记为访问
                    visited[nextx][nexty] = true;
                }

            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        char[][] grid1 = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};

        NumIslandsBfs bfs = new NumIslandsBfs();
        int i = bfs.numIslands(grid1);
        System.out.println(i);
    }

}
