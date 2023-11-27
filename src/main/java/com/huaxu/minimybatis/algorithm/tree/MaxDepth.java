package com.huaxu.minimybatis.algorithm.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2023/11/28 00:00
 */
public class MaxDepth {

    public int maxDepth(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int cnt = 1;

        while (!queue.isEmpty()) {
            depth++;
            int count = cnt;
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                cnt--;
                if (node.left == null || node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.add(node.left);
                    cnt++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    cnt++;
                }
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        MaxDepth maxDepth = new MaxDepth();
        Integer[] arr = {3, 9, 20, null, null, 15, 7};
        TreeNode treeNode = TreeUtils.constructTree(arr);

        TreeUtils.show(treeNode);

        int max = maxDepth.maxDepth(treeNode);
        System.out.println(max);
    }

}
