package com.huaxu.minimybatis.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.*;
import java.nio.*;
import java.nio.charset.StandardCharsets;


public class GetChannel {

    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        // write a file
        FileChannel fc = new FileOutputStream("data.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes(StandardCharsets.UTF_8)));
        fc.close();

        // Add to the end of the file
        fc = new RandomAccessFile("data.txt", "rw").getChannel();
        fc.position(fc.size());  //move to the end
        fc.write(ByteBuffer.wrap("Some more".getBytes(StandardCharsets.UTF_8)));
        fc.close();

        // Read the file
        fc = new FileInputStream("data.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();

        while (buff.hasRemaining()){
            System.out.println((char) buff.get());
        }

    }

}
