package com.example.demo.pr2.Task3;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(Integer.toHexString(calculateCRC16("src/main/java/com/example/demo/pr2/Task3/test.txt")).toUpperCase());
    }
    public static int calculateCRC16(String filePath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             FileChannel fileChannel = fileInputStream.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(2); // 16 бит (2 байта) для хранения контрольной суммы

            while (fileChannel.read(buffer) != -1) {
                buffer.flip(); // Переключаем буфер в режим чтения

                // Вычисляем контрольную сумму с использованием XOR
                short checksum = 0;
                while (buffer.hasRemaining()) {
                    checksum ^= buffer.getShort();
                }

                buffer.clear(); // Очищаем буфер для следующего чтения
                return checksum;
            }
        }
        return 0; // Если файл пустой, вернем ноль или другое значение по умолчанию
    }



}
