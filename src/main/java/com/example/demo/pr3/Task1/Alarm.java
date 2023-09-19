package com.example.demo.pr3.Task1;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class Alarm implements Observer<Object> {
    private static final int TEMPERATURE_THRESHOLD = 25;
    private static final int CO2_THRESHOLD = 70;
    private boolean temperatureExceedsThreshold = false;
    private boolean co2ExceedsThreshold = false;



    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(Object value) {
        if (value instanceof Integer) {
            int temperature = (int) value;
            if (temperature > TEMPERATURE_THRESHOLD) {
                System.out.println("WARNING BY TEMPERATURE: " + temperature);
                temperatureExceedsThreshold = true;
            }
        } else if (value instanceof Double) {
            double co2Level = (double) value;
            if (co2Level > CO2_THRESHOLD) {
                System.out.println("WARNING BY CO2: " + co2Level);
                co2ExceedsThreshold = true;
            }
        }

        if (temperatureExceedsThreshold && co2ExceedsThreshold) {
            System.out.println("ALARM!!!");
        }
    }


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
    }
}
