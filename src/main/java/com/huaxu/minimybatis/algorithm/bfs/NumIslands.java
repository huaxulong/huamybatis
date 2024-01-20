package com.huaxu.minimybatis.algorithm.bfs;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/1/20 14:34
 */
public class NumIslands {

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        char[][] grid1 = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};

        int i = numIslands(grid1);
        System.out.println(i);
    }

    private static int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    //找到“1”，res加一，同时淹没这个岛
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    private static void dfs(char[][] grid, int i, int j) {
        // 搜索条件，索引越界或者搜索到了0
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        //根据"每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成"，对上下左右的相邻顶点进行dfs
        dfs(grid,i - 1,j);
        dfs(grid,i + 1,j);
        dfs(grid,i,j + 1);
        dfs(grid,i,j - 1);
    }

}
