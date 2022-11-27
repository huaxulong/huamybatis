package com.huaxu.minimybatis.test.spi.javassist;

import javassist.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavassistTest {

    @Test
    public void test() throws CannotCompileException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassPool classPool = ClassPool.getDefault();
        // 创建一个名字叫Demo的类
        CtClass ctClass = classPool.makeClass("Demo");
        // 添加方法
        CtMethod ctMethod = CtNewMethod.make("public static void test() {System.out.println(\"Hello World\");}", ctClass);
        ctClass.addMethod(ctMethod);

        // 生成类
        Class aClass = ctClass.toClass();

        Object object = aClass.newInstance();

        // 获取 test() 方法
        Method m = aClass.getDeclaredMethod("test", null);

        // 执行 test() 方法
        m.invoke(object, null);


    }

    @Test
    public void testMakeClass() throws CannotCompileException, IOException {
        ClassPool cp = ClassPool.getDefault();
        CtClass ctClass = cp.makeClass("src/main/java/com.huaxu.minimybatis.spi.javaspi.Hello");
        ctClass.writeFile("./");

        System.out.println("--- end ---");

    }

    @Test
    public void testMethod() throws CannotCompileException, IOException {
        File file = new File("/Users/MrHua/workspace/stjk/mybatis/huamybatis/src/main/java");
        // 文件存储绝对路径
        String absolutePath = file.getAbsolutePath();

        System.out.println("绝对路径=" + absolutePath);

        ClassPool cp = ClassPool.getDefault();
        CtClass ctClass = cp.makeClass("com.huaxu.minimybatis.spi.javaspi.Hello");
        CtMethod ctMethod = new CtMethod(ctClass.voidType, "hello", new CtClass[]{CtClass.intType, ctClass.doubleType}, ctClass);

        ctMethod.setModifiers(Modifier.PUBLIC);

        ctClass.addMethod(ctMethod);

        ctClass.writeFile(absolutePath);
    }

    @Test
    public void addVariable() throws CannotCompileException, IOException {
        File file = new File("/Users/MrHua/workspace/stjk/mybatis/huamybatis/src/main/java");
        // 文件存储绝对路径
        String absolutePath = file.getAbsolutePath();

        System.out.println("绝对路径=" + absolutePath);

        ClassPool cp = ClassPool.getDefault();
        CtClass ctClass = cp.makeClass("com.huaxu.minimybatis.spi.javaspi.Hello");

        /**
         * 添加 public void hello(int val1, double val2){
         * }
         */
        CtMethod ctMethod = new CtMethod(ctClass.voidType, "hello", new CtClass[]{CtClass.intType, ctClass.doubleType}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctClass.addMethod(ctMethod);

        /**
         * 添加 private int value;
         */
        CtField ctField = new CtField(CtClass.intType, "value", ctClass);
        ctField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ctField);

        ctClass.writeFile(absolutePath);

    }

    @Test
    public void addGetAndSetMethod() throws CannotCompileException, IOException {
        File file = new File("/Users/MrHua/workspace/stjk/mybatis/huamybatis/src/main/java");
        // 文件存储绝对路径
        String absolutePath = file.getAbsolutePath();

        System.out.println("绝对路径=" + absolutePath);

        ClassPool cp = ClassPool.getDefault();
        CtClass ctClass = cp.makeClass("com.huaxu.minimybatis.spi.javaspi.Hello");

        /**
         * 添加 public void hello(int val1, double val2){
         * }
         */
        CtMethod ctMethod = new CtMethod(ctClass.voidType, "hello", new CtClass[]{CtClass.intType, ctClass.doubleType}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctClass.addMethod(ctMethod);

        /**
         * 添加 private int value;
         */
        CtField ctField = new CtField(CtClass.intType, "value", ctClass);
        ctField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ctField);

        /**
         * 给value 变量新增setter 方法
         * public void setValue(int val){
         *    this.value = val;
         * }
         */
        CtMethod setValueMethod = new CtMethod(CtClass.voidType, "setValue", new CtClass[]{CtClass.intType}, ctClass);
        setValueMethod.setModifiers(Modifier.PUBLIC);
        // 给变量 this.value = val1;
        setValueMethod.setBody("this.value = $1;");
        ctClass.addMethod(setValueMethod);

        /**
         *  给value 变量新增 getter 方法
         */
        CtMethod getValueMethod = new CtMethod(CtClass.intType, "getValue", new CtClass[]{}, ctClass);
        getValueMethod.setModifiers(Modifier.PUBLIC);
        getValueMethod.setBody("return this.value;");
        ctClass.addMethod(getValueMethod);

        ctClass.writeFile(absolutePath);

    }

    /**
     * 在方法体的前后插入代码
     *
     * @throws CannotCompileException
     * @throws IOException
     */
    @Test
    public void insertBodyBeforeAndAfter() throws CannotCompileException, IOException {
        File file = new File("/Users/MrHua/workspace/stjk/mybatis/huamybatis/src/main/java");
        // 文件存储绝对路径
        String absolutePath = file.getAbsolutePath();

        System.out.println("绝对路径=" + absolutePath);

        ClassPool cp = ClassPool.getDefault();
        CtClass ctClass = cp.makeClass("com.huaxu.minimybatis.spi.javaspi.Hello");

        /**
         * 添加 private int value;
         */
        CtField ctField = new CtField(CtClass.intType, "value", ctClass);
        ctField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ctField);

        /**
         * 给value 变量新增setter 方法
         * public void setValue(int val){
         *    this.value = val;
         * }
         */
        CtMethod setValueMethod = new CtMethod(CtClass.voidType, "setValue", new CtClass[]{CtClass.intType}, ctClass);
        setValueMethod.setModifiers(Modifier.PUBLIC);
        // 给变量 this.value = val1;
        setValueMethod.setBody("this.value = $1;");
        ctClass.addMethod(setValueMethod);

        /**
         *  给value 变量新增 getter 方法
         */
        CtMethod getValueMethod = new CtMethod(CtClass.intType, "getValue", new CtClass[]{}, ctClass);
        getValueMethod.setModifiers(Modifier.PUBLIC);
        getValueMethod.setBody("return this.value;");
        ctClass.addMethod(getValueMethod);

        /**
         * 添加 public void hello(int val1, double val2){
         *   System.out.println("我在前面插入了：");
         *   this.value = val1 + val2;
         *   System.out.println("我在后面插入了：");
         * }
         */
        CtMethod ctMethod = new CtMethod(ctClass.voidType, "hello1", new CtClass[]{CtClass.intType, ctClass.doubleType}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("this.value = $1 + $2;");
        ctMethod.setBody("System.out.println($args);");
        ctMethod.insertBefore("System.out.println(\"我在前面插入了：\"+$1);");
        ctMethod.insertAfter("System.out.println(\"我在后面插入了：\"+$2);");
        ctClass.addMethod(ctMethod);

        ctClass.writeFile(absolutePath);
    }


}
