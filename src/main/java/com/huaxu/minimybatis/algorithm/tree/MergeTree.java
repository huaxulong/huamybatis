package com.huaxu.minimybatis.algorithm.tree;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2023/11/14 23:28
 */
public class MergeTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, null);
        MergeTree tree = new MergeTree();
        TreeNode result = tree.getResult(root);

        System.out.println(result);
    }


    /**
     * 已知一个树的所有的叶子节点，求这颗完整的树结构
     *
     * @param root
     * @return
     */
    public TreeNode getResult(TreeNode root) {
        if (root != null) {

        }
        return new TreeNode(1, null, null);
    }

}