package com.example.demo.pr1.Task3;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class FileGenerator implements Runnable{
    private BlockingQueue<File> queue;
    private Random random = new Random();

    public FileGenerator(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String fileType="";
                switch (random.nextInt(3) + 1) {
                    case 1 -> fileType = "JSON";
                    case 2 -> fileType = "XML";
                    case 3 -> fileType = "XLS";
                }
                int fileSize = random.nextInt(91) + 10;
                File file = new File(fileType, fileSize, UUID.randomUUID().toString());
                queue.put(file);
                System.out.println(file);
                Thread.sleep(random.nextInt(901) + 100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
