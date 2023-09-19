package com.example.demo.pr3.Task4;

import com.example.demo.pr3.Task4.File;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FileProcessor {
    private String supportedFileType;

    public FileProcessor(String supportedFileType) {
        this.supportedFileType = supportedFileType;
    }

    public Observable<File> processFile(File file) {
        if (!file.getFileType().equals(supportedFileType)) {
            return Observable.empty();
        }

        return Observable.just(file)
                .map(f -> {
                    long processingTime = f.getFileSize() * 7;
                    try {
                        Thread.sleep(processingTime);
                        System.out.println(supportedFileType + "processor process "  + supportedFileType+ ": " + file.getFileSize());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return f;
                })
                .subscribeOn(Schedulers.io());
    }
}