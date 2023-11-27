package com.huaxu.minimybatis.algorithm.tree;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2023/11/23 23:50
 */
public class IsSymmetric {

    public static void main(String[] args) {
        Integer[] array = {1, 2, 2, 3, 4, 4, 3};
        TreeNode treeNode = TreeUtils.constructTree(array);
        TreeUtils.show(treeNode);

        IsSymmetric isSymmetric = new IsSymmetric();
        boolean symmetric = isSymmetric.isSymmetric(treeNode);
        System.out.println(symmetric);
    }

    /**
     * 判断是否对称二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return isSym(root.left, root.right);
    }

    /**
     * 后序遍历， 左边 == 右边
     *
     * @param left
     * @param right
     * @return
     */
    public boolean isSym(TreeNode left, TreeNode right) {
        if (left != null && right == null) {
            return false;
        } else if (left == null && right != null) {
            return false;
        } else if (left == null && right == null) {
            return true;
        } else if (left.value != right.value) {
            return false;
        } else {
            // 这种就是数值相等的情况了
            boolean outSide = isSym(left.left, right.right);
            boolean inSide = isSym(left.right, right.left);
            boolean result = outSide && inSide;
            return result;
        }
    }

}
