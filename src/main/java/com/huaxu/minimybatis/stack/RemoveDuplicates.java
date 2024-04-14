package com.huaxu.minimybatis.stack;

import java.util.LinkedList;

/**
 * @description: 删除字符串中的所有相邻重复项
 * @Author: Mr.Hua
 * @date: 2024/1/28 22:52
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        String str = "abbaca";
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        String s = removeDuplicates.removeDuplicates(str);
        System.out.println(s);
    }

    public String removeDuplicates(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (Character ch : chars) {
            if (stack.isEmpty()) {
                stack.add(ch);
            } else {
                Character peek = stack.getLast();
                if (peek == ch) {
                    stack.removeLast();
                } else {
                    stack.add(ch);
                }
            }
        }
        String result = "";
        if (!stack.isEmpty()) {
            for (int i = 0; i < stack.size(); i++) {
                result = result + stack.get(i);
            }
        }
        return result;
    }

}
