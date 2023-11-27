package com.huaxu.minimybatis.algorithm.tree;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2023/11/14 23:29
 */
public class TreeNode {

    public int value;

    public String val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
