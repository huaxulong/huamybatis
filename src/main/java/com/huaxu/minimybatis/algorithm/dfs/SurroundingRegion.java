package com.huaxu.minimybatis.algorithm.dfs;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/1/20 21:03
 */
public class SurroundingRegion {

    private boolean[][] visited;

    public void solve(char[][] board) {
        visited = new boolean[board.length][board[0].length];
        //边上出发 访问O,用visited 打标
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O') {
                dfs(board, 0, i);
            }
            if (board[board.length - 1][i] == 'O') {
                dfs(board, board.length - 1, i);
            }
        }

        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][board[0].length - 1] == 'O') {
                dfs(board, i, board[0].length - 1);
            }
        }

        //把board 中已经打标的位置修改成O，没有打标并且值等于O的位置，修改为X
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (visited[i][j]) {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        // 搜索条件，索引越界或者搜索到了 O
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == 'X') {
            return;
        }
        visited[x][y] = true;
        board[x][y] = 'X';
        //根据"每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成"，对上下左右的相邻顶点进行dfs
        dfs(board, x - 1, y);
        dfs(board, x + 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }

    public static void main(String[] args) {
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        SurroundingRegion region = new SurroundingRegion();
        region.solve(board);

        System.out.println(board);
    }

}
