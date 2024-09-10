package com.huaxu.minimybatis.stack;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/9/10 23:42
 */
public class MyStack {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        int top = myStack.top();// 返回 2
        int pop = myStack.pop();// 返回 2
        boolean empty = myStack.empty();// 返回 False
    }

    Queue<Integer> queue1;

    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
    }

    public void push(int x) {
        queue1.add(x);
    }

    public int pop() {
        return getValue();
    }

    public int top() {
        return topValue();
    }

    private int getValue() {
        // 当queue1为空的时候，需要把queue2 重新放到queue1中，然后返回queue2的最后一个元素
        if (queue1.size() == 0){
            while (queue2.size() > 0) {
                queue1.add(queue2.poll());
            }
            while (queue1.size() > 1) {
                queue2.add(queue1.poll());
            }
            return queue1.poll();
        }
        // 当queue1不为空的时候，需要把queue1的元素放到queue2中，然后返回queue1的最后一个元素
        while (queue1.size() > 1) {
            queue2.add(queue1.poll());
        }
        return queue1.poll();
    }

    private int topValue() {
        if (queue1.size() == 0){
            while (queue2.size() > 0) {
                queue1.add(queue2.poll());
            }
            while (queue1.size() > 1) {
                queue2.add(queue1.poll());
            }
            return queue1.peek();
        }
        while (queue1.size() > 1) {
            queue2.add(queue1.poll());
        }
        return queue1.peek();
    }

    public boolean empty() {
        if (queue2.size() == 0 && queue1.size() == 0){
            return true;
        }
        return false;
    }

}
