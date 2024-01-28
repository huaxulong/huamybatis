package com.huaxu.minimybatis.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description: 有效的字母异位词
 * @Author: Mr.Hua
 * @date: 2024/1/28 17:13
 */
public class IsAnagram {

    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        IsAnagram isAnagram = new IsAnagram();
        boolean anagram = isAnagram.isAnagram(s, t);
        System.out.println(anagram);
    }

    public boolean isAnagram(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (Character sChar : sChars) {
            Integer orDefault = map.getOrDefault(sChar, 0);
            map.put(sChar, orDefault + 1);
        }
        for (Character sChar : tChars) {
            Integer integer = map.get(sChar);
            if (null == integer) {
                return false;
            } else if (integer == 1) {
                map.remove(sChar);
            } else {
                map.put(sChar, integer - 1);
            }
        }
        Set<Character> keySet = map.keySet();
        if (0 == keySet.size()) {
            return true;
        }
        return false;
    }

}
