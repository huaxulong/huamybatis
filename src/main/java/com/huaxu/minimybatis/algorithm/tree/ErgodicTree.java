package com.huaxu.minimybatis.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2023/11/15 20:44
 */
public class ErgodicTree {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1, null, null);
        TreeNode node2 = new TreeNode(2, null, null);
        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node4 = new TreeNode(4, node1, node2);
        TreeNode node5 = new TreeNode(5, null, node3);
        TreeNode root = new TreeNode(6, node4, node5);


        ErgodicTree tree = new ErgodicTree();
        tree.getResult(root);

        System.out.println(list);
    }

    private static List<Integer> list = new ArrayList<>();

    /**
     * 递归法 / 迭代法 => 深度优先遍历
     * 层级法 => 广度优先遍历
     *
     * @param root
     */
    public void getResult(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.getLeft() != null) {
            getResult(root.getLeft());
        }
        if (root.getRight() != null) {
            getResult(root.getRight());
        }
        list.add(root.getValue());
    }

    /**
     * 因为递归的本质其实也是使用的是栈这种数据结构， 所以这里就使用栈的迭代法来模拟递归
     *
     * @param root
     * @return
     */
    public List<Integer> getResult1(TreeNode root) {
        ArrayList<Integer> list1 = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);

        while (stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop.getRight() != null) {
                stack.push(pop.getRight());
            }
            if (root.getLeft() != null) {
                stack.push(root.getLeft());
            }
        }
        return list1;
    }

}
