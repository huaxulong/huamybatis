package com.huaxu.minimybatis.algorithm.bfs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/1/20 17:57
 */
public class MaxAreaOfIsland {

    boolean[][] visited;
    int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    int count = 0;

    public int maxAreaOfIsland(int[][] grid) {
        visited = new boolean[grid.length][grid[0].length];
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    count = 0;
                    bfs(grid, i, j);
                    res = Math.max(res, count);
                }
            }
        }
        return res;
    }

    //将这片岛屿上的所有陆地都访问到
    public void bfs(int[][] grid, int x, int y) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        count++;
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
                if (!visited[nextx][nexty] && grid[nextx][nexty] == 1) {
                    queue.offer(new int[]{nextx, nexty});
                    // 只要加入过队列就标记为访问
                    visited[nextx][nexty] = true;
                    count++;
                }

            }
        }
    }

    public static void main(String[] args) {
        int[][] grid1 = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 1}};

        MaxAreaOfIsland bfs = new MaxAreaOfIsland();
        int maxIsland = bfs.maxAreaOfIsland(grid1);
        System.out.println(maxIsland);
    }

}
