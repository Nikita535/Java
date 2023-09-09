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
        final int CRC16_POLYNOMIAL = 0x1021;

        try (FileInputStream fis = new FileInputStream(filePath);
             FileChannel channel = fis.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int crc = 0xFFFF; // Инициализируем CRC-16 значением 0xFFFF.

            while (channel.read(buffer) != -1) {
                buffer.flip(); // Переключаем ByteBuffer в режим чтения.
                while (buffer.hasRemaining()) {
                    int data = buffer.get() & 0xFF; // Получаем младший байт данных.
                    crc ^= (data << 8); // XOR с младшим байтом данных.
                    for (int i = 0; i < 8; i++) {
                        if ((crc & 0x8000) != 0) {
                            crc = (crc << 1) ^ CRC16_POLYNOMIAL; // Сдвиг влево и XOR с полиномом, если старший бит равен 1.
                        } else {
                            crc <<= 1; // Простой сдвиг влево.
                        }
                    }
                }
                buffer.clear(); // Очищаем ByteBuffer для следующей порции данных.
            }
            return crc & 0xFFFF; // Оставляем только младшие 16 битов CRC.
        }
    }



}
