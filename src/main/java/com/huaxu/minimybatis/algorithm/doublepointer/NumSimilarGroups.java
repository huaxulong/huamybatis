package com.huaxu.minimybatis.algorithm.doublepointer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/4/23 23:48
 */
public class NumSimilarGroups {

    public int numSimilarGroups(String[] strs) {
        List<Set<String>> allList = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            boolean flag = false;
            for (int j = 0; j < allList.size(); j++) {
                Set<String> strings = allList.get(j);
                if (strings.contains(str)) {
                    Set<String> similarity = getSimilarity(str);
                    flag = true;
                    strings.addAll(similarity);
                    allList.add(strings);
                    break;
                }
            }
            if (!flag) {
                Set<String> similarity = getSimilarity(str);
                allList.add(similarity);
                cnt++;
            }
        }
        return cnt;
    }

    private Set<String> getSimilarity(String str) {
        Set<String> similarityList = new HashSet<>();
        similarityList.add(str);
        int start = 0;
        int end = 1;
        while (start < str.length() - 1) {
            String str1 = str;
            char[] chars = str1.toCharArray();
            char temp = chars[end];
            chars[end] = chars[start];
            chars[start] = temp;
            similarityList.add(new String(chars));
            if (start < str.length() - 1 && end < str.length() - 1) {
                end++;
            } else if (start < str.length() - 1 && end == str.length() - 1) {
                start++;
                end = start + 1;
            }
        }
        return similarityList;
    }

    public static void main(String[] args) {
        NumSimilarGroups similarGroups = new NumSimilarGroups();
        Set<String> abc = similarGroups.getSimilarity("abc");
        System.out.println(abc);

//        String[] strs = {"tars", "rats", "arts", "star"};

        String[] strs = {"kccomwcgcs", "socgcmcwkc", "sgckwcmcoc", "coswcmcgkc", "cowkccmsgc", "cosgmccwkc", "sgmkwcccoc", "coswmccgkc", "kowcccmsgc", "kgcomwcccs"};
        int cnt = similarGroups.numSimilarGroups(strs);
        System.out.println(cnt);
    }

}
