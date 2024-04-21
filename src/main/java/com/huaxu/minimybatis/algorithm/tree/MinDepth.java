package com.huaxu.minimybatis.algorithm.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2023/11/28 00:27
 */
public class MinDepth {

    public int minDepth(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (null != node.left) {
                    queue.add(node.left);
                }
                if (null != node.right) {
                    queue.add(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {
        MinDepth maxDepth = new MinDepth();
        Integer[] arr = {2, null, 3, null, 4, null, 5, null, 6};
        Integer[] array = {3, 9, 20, null, null, 15, 7};
        TreeNode treeNode = TreeUtils.constructTree(array);

        TreeUtils.show(treeNode);

        int max = maxDepth.minDepth(treeNode);
        System.out.println(max);
    }

}
