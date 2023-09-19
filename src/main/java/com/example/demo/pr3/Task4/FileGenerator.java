package com.example.demo.pr3.Task4;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FileGenerator {
    public Observable<File> generateFiles() {
        return Observable.create((ObservableEmitter<File> emitter) -> {
            Random random = new Random();
            while (!emitter.isDisposed()) {
                String fileType = getRandomFileType();
                int fileSize = random.nextInt(91) + 10; // Размер файла от 10 до 100
                File file = new File(fileType, fileSize);
                emitter.onNext(file);
                try {
                    Thread.sleep(random.nextInt(901) + 100); // Задержка от 100 до 1000 мс
                } catch (InterruptedException e) {
                    emitter.onError(e);
                    break;
                }
            }
            emitter.onComplete();
        }).subscribeOn(Schedulers.io());
    }


    private String getRandomFileType() {
        String[] fileTypes = {"XML", "JSON", "XLS"};
        Random random = new Random();
        return fileTypes[random.nextInt(fileTypes.length)];
    }
}