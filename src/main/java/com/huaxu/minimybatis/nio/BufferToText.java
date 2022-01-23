package com.huaxu.minimybatis.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;

public class BufferToText {

    private static final int BSIZE = 1024;

    /**
     * 既然
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        /*FileChannel fc = new FileOutputStream("data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes(StandardCharsets.UTF_8)));
        fc.close();

        fc = new FileInputStream("data2.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        buff.clear();
        fc.read(buff);
        buff.flip();

        // doesnot work
        System.out.println(buff.asCharBuffer());*/

        // Decode using this system default Charset
        /*buff.rewind();

        String encoding = System.getProperty("file.encoding");
        System.out.println("Decoded using "+ encoding + ": " + Charset.forName(encoding).decode(buff));


        fc = new FileOutputStream("data2.txt").getChannel();

        fc.write(ByteBuffer.wrap("Some Text2".getBytes(StandardCharsets.UTF_16BE)));
        fc.close();

        fc = new FileInputStream("data2.txt").getChannel();
        buff.clear();
        fc.read(buff);

        buff.flip();
        System.out.println(buff.asCharBuffer());*/

        FileChannel fc = new FileOutputStream("data2.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(24);
        buff.asCharBuffer().put("Some text3");
        fc.write(buff);
        fc.close();

        // Read and Display
        fc = new FileInputStream("data2.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());

    }

}
