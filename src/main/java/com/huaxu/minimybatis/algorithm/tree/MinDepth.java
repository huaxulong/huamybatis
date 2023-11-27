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
        boolean flag = false;
        if (root.left != null && root.right != null) {
            queue.add(root.left);
            queue.add(root.right);
            flag = true;
        } else if (root.left != null) {
            queue.add(root.left);
        } else if (root.right != null) {
            queue.add(root.right);
        }
        depth = 1;
        int cnt = queue.size();

        while (!queue.isEmpty()) {
            depth++;
            int count = cnt;
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                cnt--;
                if (flag && (node.left == null || node.right == null)) {
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
        MinDepth maxDepth = new MinDepth();
        Integer[] arr = {2, null, 3, null, 4, null, 5, null, 6};
        Integer[] array = {3, 9, 20, null, null, 15, 7};
        TreeNode treeNode = TreeUtils.constructTree(array);

        TreeUtils.show(treeNode);

        int max = maxDepth.minDepth(treeNode);
        System.out.println(max);
    }

}
