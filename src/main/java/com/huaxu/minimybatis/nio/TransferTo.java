package com.huaxu.minimybatis.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TransferTo {

    /**
     * ChannelCopy 并不是此类操作的理想方式，特殊方法 transferTo() 和 transferFrom 则允许我们将一个通道和另一个通道直接相连；
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("arguments: sourcefile destfile");
            System.exit(1);
        }
        FileChannel in = new FileInputStream(args[0]).getChannel();
        FileChannel out = new FileOutputStream(args[1]).getChannel();

        // channel 复制
        in.transferTo(0,in.size(), out);

        // OR
//        out.transferFrom(in, 0, in.size());

    }

}
