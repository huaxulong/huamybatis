package com.huaxu.minimybatis.algorithm.dfs;

/**
 * @description: 飞地数量:leetCode:1020
 * @Author: Mr.Hua
 * @date: 2024/1/20 19:46
 */
public class NumEnclavesDfs {
    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        NumEnclavesDfs numEnclaves = new NumEnclavesDfs();
        int res = numEnclaves.numEnclaves(grid);
        System.out.println(res);
    }

    public int numEnclaves(int[][] grid) {
        int res = 0;
        //首先根据dfs 将靠近陆地的方块修改成0
        // x = 0, x = grid.length - 1  的情况下
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i] == 1) {
                dfs(grid, 0, i);
            }
            if (grid[grid.length - 1][i] == 1){
                dfs(grid, grid.length - 1, i);
            }
        }

        // y = 0, y= grid[0].length - 1
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == 1){
                dfs(grid, i, 0);
            }
            if (grid[i][grid[0].length - 1] == 1){
                dfs(grid, i, grid[0].length - 1);
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

    private void dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {
            return;
        }
        grid[x][y] = 0;
        //根据"每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成"，对上下左右的相邻顶点进行dfs
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
    }
}
