package com.example.demo.pr1.Task1;

import java.util.concurrent.Callable;

public class MaxFinderCallableTask implements Callable<Integer> {
    private int[] arr;
    private int start;
    private int end;

    public MaxFinderCallableTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int max = Integer.MIN_VALUE;
        for (int j = start; j < end; j++) {
            if (arr[j] > max) {
                max = arr[j];
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return max;
    }
}
