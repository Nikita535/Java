
package com.example.demo.pr3.Task4;
import com.example.demo.pr3.Task4.File;
import io.reactivex.rxjava3.subjects.PublishSubject;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Queue {
    private BlockingQueue<File> queue;

    public Queue(int capacity) {
        queue = new ArrayBlockingQueue<>(capacity);
    }

    public void enqueue(File file) throws InterruptedException {
        System.out.println("File added " + file.getFileType());
        queue.put(file);
    }

    public File dequeue() throws InterruptedException {
        return queue.take();
    }
}