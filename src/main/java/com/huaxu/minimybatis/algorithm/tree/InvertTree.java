package com.huaxu.minimybatis.algorithm.tree;

import java.util.Stack;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2023/11/23 23:37
 */
public class InvertTree {

    public static void main(String[] args) {
        Integer[] array = {4, 2, 7, 1, 3, 6, 9};
        TreeNode treeNode = TreeUtils.constructTree(array);
        InvertTree invertTree = new InvertTree();
        System.out.println("翻转前");
        TreeUtils.show(treeNode);
        TreeNode treeNode1 = invertTree.invertTree(treeNode);
        System.out.println("翻转hou");
        TreeUtils.show(treeNode1);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tree = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
            TreeNode left = pop.left;
            TreeNode right = pop.right;
            pop.left = right;
            pop.right = left;
        }
        return tree;
    }

}
