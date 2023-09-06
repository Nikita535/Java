package com.example.demo.pr1.Task3;

public class File {
    private String fileFormat;
    private int fileSize;
    private String id;

    public String getId() {
        return id;
    }

    public File(String fileFormat, int fileSize, String id) {
        this.fileFormat = fileFormat;
        this.fileSize = fileSize;
        this.id = id;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public int getFileSize() {
        return fileSize;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileFormat='" + fileFormat + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
