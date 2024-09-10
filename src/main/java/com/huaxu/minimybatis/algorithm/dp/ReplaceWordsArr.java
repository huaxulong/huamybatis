package com.huaxu.minimybatis.algorithm.dp;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/8/24 21:51
 */
public class ReplaceWordsArr {

    public static void main(String[] args) {
        ReplaceWordsArr replaceWordsArr = new ReplaceWordsArr();
//        ArrayList<String> dictionary = Lists.newArrayList("cat", "bat", "rat");
        ArrayList<String> dictionary = Lists.newArrayList("a", "aa", "aaa", "aaaa");
//        String sentence = "the cattle was rattled by the battery";
        String sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa";
        String str = replaceWordsArr.replaceWords(dictionary, sentence);

        System.out.println(str);
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Collections.sort(dictionary, (o1, o2) -> o2.length() - o1.length());

        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String target = words[i];
            for (String senten : dictionary) {
                if (target.startsWith(senten)) {
                    words[i] = senten;
                    break;
                }
            }
        }
        return String.join(" ", words);
    }

}
