package com.huaxu.minimybatis.algorithm.graph;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/8/25 10:07
 */
public class AStart {

    static int[][] dir = {{1, 2}, {2, 1}, {-1, 2}, {-2, 1}, {1, -2}, {2, -1}, {-1, -2}, {-2, -1}};
    static int[][] moves;
    static int b1, b2;

    static int heuristic(KnightNode node) {
        return (node.x - b1) * (node.x - b1) + (node.y - b2) * (node.y - b2);
    }

    static void astar(KnightNode start) {
        PriorityQueue<KnightNode> que = new PriorityQueue<>();
        que.add(start);
        while (!que.isEmpty()) {
            KnightNode cur = que.poll();
            if (cur.x == b1 && cur.y == b2) {
                break;
            }
            for (int[] d : dir) {
                int nextX = cur.x + d[0];
                int nextY = cur.y + d[1];
                if (nextX < 1 || nextX > 1000 || nextY < 1 || nextY > 1000) {
                    continue;
                }
                if (moves[nextX][nextY] == 0) {
                    moves[nextX][nextY] = moves[cur.x][cur.y] + 1;
                    KnightNode next = new KnightNode(nextX, nextY);
                    next.g = cur.g + 5;
                    next.h = heuristic(next);
                    next.f = next.g + next.h;
                    que.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        int a1 = 0, a2 = 0;
        b1 = 10;
        b2 = 10;
        moves = new int[1001][1001];
        KnightNode start = new KnightNode(a1, a2);
        start.g = 0;
        start.h = heuristic(start);
        start.f = start.g + start.h;
        astar(start);
    }

    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // n表示 1个骑士的人，或者数量，或者🐴的个数
        int n = scanner.nextInt();
        while (n-- > 0) {
            int a1 = scanner.nextInt();
            int a2 = scanner.nextInt();
            b1 = scanner.nextInt();
            b2 = scanner.nextInt();
            moves = new int[1001][1001];
            // a1、a2 是骑士的初始位置，b1、b2 是骑士的目标位置
            KnightNode start = new KnightNode(a1, a2);
            start.g = 0;
            start.h = heuristic(start);
            start.f = start.g + start.h;
            astar(start);

            System.out.println(moves[b1][b2]);
        }
    }

}

class KnightNode implements Comparable<KnightNode> {
    int x, y;
    int g, h, f;

    public KnightNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(KnightNode other) {
        return Integer.compare(other.f, this.f);
    }
}
