package com.huaxu.minimybatis.algorithm.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/7/28 11:17
 */
public class AllPatch {

    public static void main(String[] args) {
        AllPatch patch = new AllPatch();
        patch.input();
    }

    private void input() {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();
        System.out.println(String.format("M = %d, N = %d", M, N));

        List<Integer>[] graph = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i = 0; i < N; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            System.out.println(String.format("x = %d, y = %d", x, y));
            List<Integer> list = graph[x - 1];
            list.add(y);
        }

        System.out.println("graph:" + Arrays.toString(graph));
    }

}
