package com.huaxu.minimybatis.juc.lock.countlatch;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-09-18 3:29 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class BreakTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            /*for (int j = 0; j < 10; j++) {
                if (i == 5 && j == 5) {
                    break;
                    //continue;
                    //return;
                }
                System.out.println(" i = " + i + " , j = " + j + "\n");
            }*/
            if (i == 5) {
                //break;
                continue;
                //return;
            }
            System.out.println(" i = " + i + "\n");
        }
        System.out.println("********");
        System.out.println("++++++++");
    }

}
