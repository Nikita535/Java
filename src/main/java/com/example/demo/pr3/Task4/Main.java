package com.example.demo.pr3.Task4;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        FileGenerator fileGenerator = new FileGenerator();
        Queue fileQueue = new Queue(5);
        FileProcessor xmlFileProcessor = new FileProcessor("XML");
        FileProcessor jsonFileProcessor = new FileProcessor("JSON");
        FileProcessor xlsFileProcessor = new FileProcessor("XLS");

        fileGenerator.generateFiles()
                .subscribeOn(Schedulers.io())
                .subscribe(file -> {
                    try {
                        fileQueue.enqueue(file);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        Observable.interval(1, TimeUnit.SECONDS)
                .flatMap(tick -> {
                    try {
                        return Observable.just(fileQueue.dequeue());
                    } catch (InterruptedException e) {
                        return Observable.empty();
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(file -> {
                    if (file.getFileType().equals("XML")) {
                        xmlFileProcessor.processFile(file).subscribe();
                    } else if (file.getFileType().equals("JSON")) {
                        jsonFileProcessor.processFile(file).subscribe();
                    } else if (file.getFileType().equals("XLS")) {
                        xlsFileProcessor.processFile(file).subscribe();
                    }
                });

        Thread.sleep(60000); // Работаем в течение 60 секунд
    }
}