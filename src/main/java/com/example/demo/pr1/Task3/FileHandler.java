package com.example.demo.pr1.Task3;


import java.util.concurrent.BlockingQueue;

public class FileHandler implements Runnable{

    private BlockingQueue<File> queue;
    private String allowedFileType;

    public FileHandler(BlockingQueue<File> queue, String allowedFileType) {
        this.queue = queue;
        this.allowedFileType = allowedFileType;
    }

    @Override
    public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    if (file.getFileFormat().equals(allowedFileType)) {
                        int processingTime = file.getFileSize() * 7;
                        Thread.sleep(processingTime);
                        System.out.println("Processed " +
                                file.getFileFormat()+" file id "+
                                file.getFileSize() + " file of size " +
                                file.getFileSize() + " in " +
                                processingTime + " ms");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
}
