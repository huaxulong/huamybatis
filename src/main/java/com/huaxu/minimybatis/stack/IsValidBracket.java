package com.huaxu.minimybatis.stack;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @description: 有效括号
 * @Author: Mr.Hua
 * @date: 2024/1/28 22:22
 */
public class IsValidBracket {

    public static void main(String[] args) {
        String s = "(){[]}{}";
        IsValidBracket isValidBracket = new IsValidBracket();
        boolean valid = isValidBracket.isValid(s);
        System.out.println(valid);
    }

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        char[] charArray = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (Character ch : charArray) {
            if (stack.isEmpty()){
                stack.push(ch);
            }else {
                if (isMatch(stack.peek(), ch, map)){
                    stack.pop();
                }else {
                    stack.push(ch);
                }
            }
        }
        return stack.isEmpty() ? true : false;
    }

    public boolean isMatch(char key, char val, Map<Character, Character> map) {
        if (map.get(key) != null && map.get(key) == val){
            return true;
        }
        return false;
    }

}
