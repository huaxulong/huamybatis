package com.huaxu.minimybatis.algorithm.dfs;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/6/11 22:29
 */
public class IslandsNumDfs {

    boolean[][] visited;
    int dir[][] = {
            {0, 1}, //right
            {1, 0}, //down
            {-1, 0}, //up
            {0, -1} //left
    };

    public int numIslands(char[][] grid) {
        int count = 0;
        visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visited[i][j] == false && grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int x, int y) {
        // 已经被遍历过，或者不能继续遍历了，进行剪枝
        if (visited[x][y] == true || grid[x][y] == '0') {
            return;
        }

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if (nextX < 0 || nextY < 0 || nextX >= grid.length || nextY >= grid[0].length)
                continue;
            dfs(grid, nextX, nextY);
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        IslandsNumDfs islandsNumDfs = new IslandsNumDfs();
        int cnt = islandsNumDfs.numIslands(grid);
        System.out.println(cnt);

    }

}
