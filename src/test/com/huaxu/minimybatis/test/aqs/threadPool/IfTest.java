package com.huaxu.minimybatis.test.aqs.threadPool;

import java.util.Scanner;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-26 11:09 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class IfTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cnt = scanner.nextInt();
        if (cnt == 0){
            System.out.println(cnt);
            cnt = 2;
        }else if (cnt == 2){
            System.out.println(cnt);
        }else {
            System.out.println("3");
        }
        System.out.println("end = "+ cnt);

    }

}
