package com.example.demo.pr3.Task2;

import io.reactivex.rxjava3.core.Observable;


import java.util.Random;

public class Task2 {
    public static void main(String[] args) {
//        task11();
//        task12();
        task13();
    }

    private static void task11(){
        Observable<Integer> originalObservable = Observable.range(0, 1000).map(i -> (int) (Math.random() * 1001));
        Observable<Integer> squaredObservable = originalObservable.map(x -> x * x);
        squaredObservable.subscribe(System.out::println);
    }

    private static void task12(){
        Observable<Integer> originalObservable = Observable.range(0, 1000).map(i -> (int) (Math.random() * 1001));
        Observable<Integer> squaredObservable = originalObservable.filter(x -> x > 500);;
        squaredObservable.subscribe(System.out::println);
    }

    private static void task13(){
        Random random = new Random();

        int randomCount = random.nextInt(1001); // Генерируем случайное количество чисел от 0 до 1000

        Observable<Integer> countObservable = Observable.just(randomCount); // Создаем поток с количеством

        countObservable.subscribe(System.out::println);
    }


}
