package com.example.demo.pr1.Task1;

import java.util.concurrent.RecursiveTask;

public class MaxFinderFork extends RecursiveTask<Integer> {
    private int[] arr;
    private int start;
    private int end;

    public MaxFinderFork(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int length = end - start;

        if (length <= 100) {
            int max = Integer.MIN_VALUE;
            for (int i = start; i < end; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return max;
        } else {
            int mid = (start + end) / 2;
            MaxFinderFork leftTask = new MaxFinderFork(arr, start, mid);
            MaxFinderFork rightTask = new MaxFinderFork(arr, mid, end);

            leftTask.fork();
            int rightMax = rightTask.compute();
            int leftMax = leftTask.join();

            return Math.max(leftMax, rightMax);
        }
    }
}
