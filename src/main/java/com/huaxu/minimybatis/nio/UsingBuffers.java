package com.huaxu.minimybatis.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class UsingBuffers {


    private static void symmetricScramble(CharBuffer buffer){
        while (buffer.hasRemaining()){
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            buffer.reset();
            buffer.put(c2).put(c1);
        }
    }

    /**
     * Buffer 由数据和可以高效的访问及操纵这些数据的四个索引组成，这四个索引是：
     * mark(标记)
     * position(位置)
     * limit（界限）
     * capacity(容量)
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        char[] data = "UsingBuffers".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);

        System.out.println(cb.rewind());

        symmetricScramble(cb);
        System.out.println(cb.rewind());

        symmetricScramble(cb);
        System.out.println(cb.rewind());


        /**
         * capacity() : 返回缓冲区容量
         * clear() :  清空缓冲区， 将 position 设置成0， limit 设置为容量。 我们可以调用此方法覆写缓冲区
         * flip()  : 将limit设置成position , position 设置为0. 此方法用于准备从缓冲区读取已经写入的数据；
         * limit()  : 返回limit 数据
         * limit(int lim) 设置limit的值
         * mark()  : 将mark 设置成 position
         * position()  : 返回 position的值
         * position(int pos)  : 设置position的值
         * remaining()  返回 limit - position
         * hasRemaining()   : 若有介于 position 和 limit 之间的元素，则返回 true
         *
         */

    }

}
