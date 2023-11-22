package com.huaxu.minimybatis.algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2023/11/22 22:57
 */
public class LevelOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        Queue<TreeNode> queue1 = new ArrayDeque<>();
        dealLevel(queue, queue1);
        return largeList;
    }


    List<List<Integer>> largeList = new ArrayList<>();

    private void dealLevel(Queue<TreeNode> queue, Queue<TreeNode> queue1) {
        if (queue.isEmpty() && queue1.isEmpty()){
            return;
        }
        if (queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            // 处理queue1
            while (!queue1.isEmpty()){
                TreeNode peek = queue1.poll();
                list.add(peek.getValue());
                if (peek.getLeft() != null){
                    queue.add(peek.getLeft());
                }
                if (peek.getRight() != null){
                    queue.add(peek.getRight());
                }
            }
            largeList.add(list);
            dealLevel(queue, queue1);
        } else if (queue1.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            while (!queue.isEmpty()){
                TreeNode peek = queue.poll();
                list.add(peek.getValue());
                if (peek.getLeft() != null){
                    queue1.add(peek.getLeft());
                }
                if (peek.getRight() != null){
                    queue1.add(peek.getRight());
                }
            }
            largeList.add(list);
            dealLevel(queue, queue1);
        }
    }

    public static void main(String[] args) {
        String s = "[3,5,1,6,2,0,8,null,null,7,4]";
        TreeNode treeNode = TreeUtils.strToTree(s);
        TreeUtils.show(treeNode);

        LevelOrder levelOrder = new LevelOrder();
        List<List<Integer>> lists = levelOrder.levelOrder(treeNode);

        lists.forEach(list1 -> {
            list1.forEach(cnt -> System.out.print(cnt + "\t"));
            System.out.println("\t");
        });
    }

}
