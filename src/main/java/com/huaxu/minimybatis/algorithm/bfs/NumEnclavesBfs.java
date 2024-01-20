package com.huaxu.minimybatis.algorithm.bfs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @description:leetcode：1020,
 * @Author: Mr.Hua
 * @date: 2024/1/20 20:42
 */
public class NumEnclavesBfs {

    int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numEnclaves(int[][] grid) {
        int res = 0;
        //首先根据dfs 将靠近陆地的方块修改成0
        // x = 0, x = grid.length - 1  的情况下
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i] == 1) {
                bfs(grid, 0, i);
            }
            if (grid[grid.length - 1][i] == 1){
                bfs(grid, grid.length - 1, i);
            }
        }

        // y = 0, y= grid[0].length - 1
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == 1){
                bfs(grid, i, 0);
            }
            if (grid[i][grid[0].length - 1] == 1){
                bfs(grid, i, grid[0].length - 1);
            }
        }


        //然后根据dfs 计算出飞地的方块数，直接遍历方块数为1的数量即可
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] ==  1){
                    res++;
                }
            }
        }

        return res;
    }

    private void bfs(int[][] grid, int x, int y) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        // 这里用 grid[x][y] = 0;来代替 visited = true；
        grid[x][y] = 0;
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int m = cur[0];
            int n = cur[1];
            for (int i = 0; i < 4; i++) {
                int nextx = m + move[i][0];
                int nexty = n + move[i][1];
                if (nexty < 0 || nextx == grid.length || nextx < 0 || nexty == grid[0].length) {
                    continue;
                }
                if (grid[nextx][nexty] == '1'){
                    queue.offer(new int[]{nextx, nexty});
                }
            }
        }

    }

    public static void main(String[] args) {
        NumEnclavesBfs bfs = new NumEnclavesBfs();
        int[][] grid = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        int res = bfs.numEnclaves(grid);
        System.out.println(res);
    }

}
