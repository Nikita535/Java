package com.example.demo.pr3.Task1;



import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        Temperature temperatureSensor = new Temperature();
        CO2 co2Sensor = new CO2();
        Alarm alarm = new Alarm();

        Observable<Integer> temperatureObservable = Observable.interval(1, TimeUnit.SECONDS)
                .map(i -> temperatureSensor.getTemperature())
                .subscribeOn(Schedulers.io());

        Observable<Double> co2Observable = Observable.interval(1, TimeUnit.SECONDS)
                .map(i -> co2Sensor.getCO2Level())
                .subscribeOn(Schedulers.io());

        temperatureObservable.subscribe(alarm);
        co2Observable.subscribe(alarm);

        try {
            sleep(60000); // Работаем в течение 60 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
