package com.example.demo.pr1;

import java.util.Scanner;
import java.util.concurrent.*;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        while (true) {
            String input = scanner.nextLine();
            int number = Integer.parseInt(input);
            Future<Integer> future = executorService.submit(() -> {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
                return (int) Math.pow(number,2);
            });

            try {
                int squaredNumber = future.get();
                System.out.println(squaredNumber);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}