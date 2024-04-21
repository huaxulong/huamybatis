package com.huaxu.minimybatis.algorithm.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/4/17 20:52
 */
public class OpenLock {

    public int openLock(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < deadends.length; i++) {
            set.add(deadends[i]);
        }
        if (set.contains("0000")) {
            return -1;
        }
        if (target.equals("0000")) {
            return 0;
        }
        queue.add("0000");
        Set<String> visits = new HashSet<>();
        visits.add("0000");
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String str = queue.poll();

                for (int j = 0; j < 4; j++) {
                    //往上移动
                    String upStr = upStr(str, j);
                    if (target.equals(upStr)) {
                        return step;
                    }
                    if (!visits.contains(upStr) && !set.contains(upStr)) {
                        queue.add(upStr);
                        visits.add(upStr);
                    }
                    //往下移动
                    String downStr = downStr(str, j);
                    if (target.equals(downStr)) {
                        return step;
                    }
                    if (!visits.contains(downStr) && !set.contains(downStr)) {
                        queue.add(downStr);
                        visits.add(downStr);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String upStr(String str, int index) {
        char[] chars = str.toCharArray();
        if (chars[index] == '9') {
            chars[index] = '0';
        } else {
            chars[index] = (char) (chars[index] + 1);
        }
        return new String(chars);
    }

    private String downStr(String str, int index) {
        char[] chars = str.toCharArray();
        if (chars[index] == '0') {
            chars[index] = '9';
        } else {
            chars[index] = (char) (chars[index] - 1);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        OpenLock lock = new OpenLock();
        String[] array = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        int cnt = lock.openLock(array, target);
        System.out.println(cnt);
    }

}
