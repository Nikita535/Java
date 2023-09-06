package com.example.demo.pr2.Task2;

import java.io.File;
import java.io.RandomAccessFile;

public class CreateFile {
       private  String filePath;
        private long fileSize ;

    public CreateFile(String filePath, long fileSize) {
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public void createFileMb() {
            try {
                File file = new File(filePath);
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.setLength(fileSize);
                randomAccessFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public String getFilePath() {
        return filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    @Override
    public String toString() {
        return "CreateFile{" +
                "filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}