package com.huaxu.minimybatis.nio;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * @description: MappedIO
 * <p></p>
 * @author: DongxuHua
 * @create: at 2022-01-24 11:03 上午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class MappedIO {

    private static int numOfInts = 4000000;

    private static int numOfUbuffInts = 200000;

    private abstract static class Tester {
        private String name;

        public Tester(String name) {
            this.name = name;
        }

        public void runTest() {
            System.out.println(name + " : ");
            long start = System.nanoTime();
            try {
                test();
                long duration = System.nanoTime() - start;
//            System.out.println("%.2f\n", duration / 1.0e9);
                System.out.println("duration : " + duration);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public abstract void test() throws IOException;

    }

    private static Tester[] tests = {
            new Tester("Stream Write") {

                @Override
                public void test() throws IOException {
                    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("/Users/dongxuhua/temp.tmp"))));
                    for (int i = 0; i < numOfInts; i++) {
                        dos.writeInt(i);
                    }
                    dos.close();
                }
            },
            new Tester("Mapped Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile("/Users/dongxuhua/temp.tmp", "rw").getChannel();
                    IntBuffer intBuffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
                    for (int i = 0; i < numOfInts; i++) {
                        intBuffer.put(i);
                    }
                    fc.close();
                }
            },
            new Tester("Stream read") {
                @Override
                public void test() throws IOException {
                    DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(new File("/Users/dongxuhua/temp.tmp"))));
                    for (int i = 0; i < numOfInts; i++) {
                        dataInputStream.readInt();
                    }
                    dataInputStream.close();
                }
            },
            new Tester("Mapped Read") {
                @Override
                public void test() throws IOException {
                    FileChannel fileChannel = new FileInputStream(new File("/Users/dongxuhua/temp.tmp")).getChannel();
                    IntBuffer intBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size()).asIntBuffer();
                    while (intBuffer.hasRemaining()) {
                        intBuffer.get();
                    }
                    fileChannel.close();
                }
            },
            new Tester("Stream Read/Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fileChannel = new RandomAccessFile("/Users/dongxuhua/temp.tmp", "rw").getChannel();
                    final IntBuffer intBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size()).asIntBuffer();
                    intBuffer.put(0);
                    for (int i = 1; i < numOfUbuffInts; i++) {
                        intBuffer.put(intBuffer.get(i-1));
                    }
                    fileChannel.close();
                }
            }

    };


    public static void main(String[] args) {
        for (Tester test : tests){
            test.runTest();
        }

        System.out.println("abc");
    }

}
