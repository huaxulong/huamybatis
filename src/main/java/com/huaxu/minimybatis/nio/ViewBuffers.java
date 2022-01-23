package com.huaxu.minimybatis.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class ViewBuffers {

    /**
     * ByteBuffer 通过一个被 “包装” 过8字节数组产生，然后通过各种不同的基本类型的视图缓冲器显示了出来。
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{0,0,0,0,0,0,0,'a'});
        bb.rewind();

        while (bb.hasRemaining()){
            System.out.print(bb.position() + " -> " + bb.get() + ", ");
        }
        System.out.println();

        bb.rewind();
        System.out.println("CharBuffer  -->  ");
        CharBuffer cb = bb.asCharBuffer();
        while (cb.hasRemaining()){
            System.out.print(cb.position() + " -> " + cb.get() + ", ");
        }
        System.out.println();

    }

}
