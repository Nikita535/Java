package com.example.demo.pr1.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Task1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = new int[10000];
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1001); // Генерируем случайное число от 0 до 1000
        }

        long startTime = System.currentTimeMillis();
        System.out.println(findMaxOne(arr));
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения (последовательно): " + (endTime - startTime) + " милисекунд");


        startTime = System.currentTimeMillis();
        System.out.println(findMaxTwo(arr));
        endTime = System.currentTimeMillis();
        System.out.println("Время выполнения (Thread,ExecutorService): " + (endTime - startTime) + " милисекунд");

        startTime = System.currentTimeMillis();
        System.out.println(findMaxThird(arr));
        endTime = System.currentTimeMillis();
        System.out.println("Время выполнения (Fork/Join framework): " + (endTime - startTime) + " милисекунд");

    }

    public static int findMaxOne(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return max;
    }

    public static int findMaxTwo(int[] arr) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<Integer>> futures = new ArrayList<>();


        for (int i = 0; i < 5; i++) {
            int start = i * (arr.length / 5);
            int end = (i == 5 - 1) ? arr.length : (i + 1) * (arr.length / 5);
            futures.add(executorService.submit(new MaxFinderCallableTask(arr,start,end)));
        }

        int max = Integer.MIN_VALUE;
        for (Future<Integer> future : futures) {
            int threadMax = future.get();
            if (threadMax > max) {
                max = threadMax;
            }
        }

        executorService.shutdown();
        return max;
    }


    public static int findMaxThird(int[] arr) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        return forkJoinPool.invoke(new MaxFinderFork(arr, 0, arr.length));
    }


}
