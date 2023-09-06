package com.example.demo.pr1.Task3;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(5);

        Thread generatorThread = new Thread(new FileGenerator(queue));
        Thread xmlHandlerThread = new Thread(new FileHandler(queue, "XML"));
        Thread jsonHandlerThread = new Thread(new FileHandler(queue, "JSON"));
        Thread xlsHandlerThread = new Thread(new FileHandler(queue, "XLS"));

        generatorThread.start();
        xmlHandlerThread.start();
        jsonHandlerThread.start();
        xlsHandlerThread.start();
    }
}
