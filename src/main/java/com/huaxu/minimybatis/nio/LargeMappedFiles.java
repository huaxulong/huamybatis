package com.huaxu.minimybatis.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存映射文件：  这个过程有系统调用 mmap()
 */
public class LargeMappedFiles {

    static int length = 0x8FFFFF;

    /**
     * 演示只需要取部分数据，这个程序穿件的文件为128M，这可能比操作系统所允许一次载入内存的空间大。
     * 但似乎我们可以一次访问到整个文件，因为只有一部分文件放入了内存，文件的其他部分被交换了出去。用这种方式，很大的文件（可达2GB）也可以很容易的修改。
     * 注意底层操作系统的文件映射工具是用来最大化的提高性能。
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        MappedByteBuffer out = new RandomAccessFile("test1.dat", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);

        for (int i = 0; i < length; i++) {
            out.put((byte) 'x');
        }

        for (int i = length / 2; i < length / 2 + 6; i++) {
            System.out.println((byte) out.get(i));
        }
    }

}
