package com.huaxu.minimybatis.array;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/1/28 16:54
 */
public class GenerateMatrix {

    public static void main(String[] args) {
        GenerateMatrix generateMatrix = new GenerateMatrix();
        int[][] ints = generateMatrix.generateMatrix(3);
        System.out.println(ints);
    }

    public int[][] generateMatrix(int n) {
        if (n > 0) {
            // 上排参数
            for (int i = 0; i < n; i++) {

            }
            return new int[][]{};
        }
        return new int[][]{};
    }

}
