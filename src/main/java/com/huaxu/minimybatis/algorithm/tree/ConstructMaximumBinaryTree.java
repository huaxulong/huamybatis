package com.huaxu.minimybatis.algorithm.tree;

/**
 * @description: 654. 最大二叉树
 * @Author: Mr.Hua
 * @date: 2024/8/24 23:12
 */
public class ConstructMaximumBinaryTree {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 6, 0, 5};

        TreeNode node = new ConstructMaximumBinaryTree().constructMaximumBinaryTree(nums);
        TreeUtils.show(node);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode construct = construct(nums, 0, nums.length - 1);

        return construct;
    }

    public TreeNode construct(int[] nums, int begin, int end) {
        if (begin > end) {
            return null;
        }
        // 找到最大值
        int maxIndex = begin;
        for (int i = begin + 1; i <= end; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        // 构建节点
        TreeNode node = new TreeNode(nums[maxIndex]);
        // 构建左子树
        node.left = construct(nums, begin, maxIndex - 1);
        // 构建右子树
        node.right = construct(nums, maxIndex + 1, end);
        return node;
    }

}
