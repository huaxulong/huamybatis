package com.huaxu.minimybatis.algorithm.tree;

import com.google.common.collect.Lists;

import java.util.*;

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
        List<Integer> result2 = tree.getResult2(root);

        List<Integer> result = tree.getResult(root);
        result.forEach(System.out::println);
    }


    /**
     * 递归法 / 迭代法 => 深度优先遍历
     * 层级法 => 广度优先遍历
     *
     * @param root
     */
    private void getResult(TreeNode root, List<Integer> list) {
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

    public List<Integer> getResult(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        getResult(root, list);
        return list;
    }

    /**
     * 因为递归的本质其实也是使用的是栈这种数据结构， 所以这里就使用栈的迭代法来模拟递归.
     * 比如中序遍历 -》 根 =》 左 =》 右
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

    /**
     * 用栈的方式进行中序遍历： 左 =》中 =》 右
     * 5
     * 4       6
     * 1       2
     *
     * @param root
     * @return
     */
    public List<Integer> getResult2(TreeNode root) {
        ArrayList<Integer> result = Lists.newArrayList();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.getLeft();
            } else {
                cur = stack.pop();
                result.add(cur.getValue());
                cur = cur.getRight();
            }
        }
        return result;
    }

    /**
     * leetcode:102 二叉树的层次遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<Integer> list1 = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode peek = queue.peek();
            list1.add(peek.getValue());
        }
        return null;
    }

}
