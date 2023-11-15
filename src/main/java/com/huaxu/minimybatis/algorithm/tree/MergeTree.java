package com.huaxu.minimybatis.algorithm.tree;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2023/11/14 23:28
 */
public class MergeTree {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1, null, null);
        TreeNode node2 = new TreeNode(2, null, null);
        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node4 = new TreeNode(0, node1, node2);
        TreeNode node5 = new TreeNode(0, null, node3);
        TreeNode root = new TreeNode(0, node4, node5);


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
        if (root == null) {
            return root;
        }
        if (root.getLeft() != null) {
            getResult(root.getLeft());
        }
        if (root.getRight() != null) {
            getResult(root.getRight());
        }
        if (root.getValue() != 0){
            return root;
        }else {
            root.setValue((root.getLeft() != null ? root.getLeft().getValue() : 0) + (root.getRight() != null ? root.getRight().getValue() : 0));
            return root;
        }
    }

}