package com.huaxu.minimybatis.utils;

import org.apache.commons.cli.*;

public class CommandLineTest {

    public static void main(String[] args) {
        new CommandLineTest().parseArgs(args);
    }

    public void parseArgs(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("谢谢使用噶");
        }));
        try {
            CommandLineParser parser = new GnuParser( );

            Options options = new Options();
            options.addOption("help","help",false,"Print this usage information");
            options.addOption("c", "cfg", false, "config Absolute Path");

            CommandLine commandLine = null;
            commandLine = parser.parse( options, args );
            if( commandLine.hasOption("help") ) {
                System.out.println("这是一个优秀的帮助文档");
                System.exit(0);
            }

            if( commandLine.hasOption("c") ) {
                System.out.println("所以我每一个都选c");
                System.exit(0);
            }

        } catch (ParseException e) {
            System.out.println("请输入正确的参数");
            System.exit(0);
        }catch (Exception e){
            System.out.println("遇到了意外的情况，请重新输入");
        }
    }

}
