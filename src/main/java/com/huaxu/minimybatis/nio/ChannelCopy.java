package com.huaxu.minimybatis.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 如果我们打算使用缓冲器执行进一步的read() 操作，我们也必须得调用 clear() 来为每个read() 做好准备。
 */
public class ChannelCopy {

    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("arguments: sourcefile destfile");
            System.exit(1);
        }
        FileChannel in = new FileInputStream(args[0]).getChannel();
        FileChannel out = new FileOutputStream(args[1]).getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(BSIZE);
        while (in.read(byteBuffer) != -1){
            byteBuffer.flip();
            out.write(byteBuffer);
            byteBuffer.clear();
        }


    }

}
