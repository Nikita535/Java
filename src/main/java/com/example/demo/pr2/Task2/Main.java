package com.example.demo.pr2.Task2;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException {
        CreateFile file = new CreateFile("src/main/java/com/example/demo/pr2/Task2/test.txt", 100 * 1024 * 1024);
        file.createFileMb();


        //1
        long startTime = System.currentTimeMillis();
        copyFileWithInputOutputStreams();
        long endTime = System.currentTimeMillis();
        System.out.println("Время копирования (Input/Output): " + (endTime - startTime) + " миллисекунд");



        //2
        startTime = System.currentTimeMillis();
        copyFileUsingChannel();
        endTime = System.currentTimeMillis();
        System.out.println("Время копирования (FileChannel): " + (endTime - startTime) + " миллисекунд");


        //3
        startTime = System.currentTimeMillis();
        copyFileApacheCommonsIO();
        endTime = System.currentTimeMillis();
        System.out.println("Время копирования (Apache commons IO): " + (endTime - startTime) + " миллисекунд");

        //4
        startTime = System.currentTimeMillis();
        copyFileJavaFiles();
        endTime = System.currentTimeMillis();
        System.out.println("Время копирования (Java File): " + (endTime - startTime) + " миллисекунд");
    }


    public static void copyFileWithInputOutputStreams()
    {
        File srcFile = new File("src/main/java/com/example/demo/pr2/Task2/test.txt");
        File destFile = new File("src/main/java/com/example/demo/pr2/Task2/test1.txt");
        try (InputStream is = new FileInputStream(srcFile);
             OutputStream os = new FileOutputStream(destFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void copyFileUsingChannel() throws IOException {
        File srcFile = new File("src/main/java/com/example/demo/pr2/Task2/test.txt");
        File destFile = new File("src/main/java/com/example/demo/pr2/Task2/test2.txt");

        try (FileChannel sourceChannel = new FileInputStream(srcFile).getChannel();
             FileChannel destChannel = new FileOutputStream(destFile).getChannel()) {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static void copyFileApacheCommonsIO() {
        File srcFile = new File("src/main/java/com/example/demo/pr2/Task2/test.txt");
        File destFile = new File("src/main/java/com/example/demo/pr2/Task2/test3.txt");
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void copyFileJavaFiles() throws IOException {
        File srcFile = new File("src/main/java/com/example/demo/pr2/Task2/test.txt");
        File destFile = new File("src/main/java/com/example/demo/pr2/Task2/test4.txt");
        Files.copy(srcFile.toPath(), destFile.toPath());
    }

}
