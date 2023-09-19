package com.example.demo.pr3.Task1;


import io.reactivex.rxjava3.core.Observable;

import java.util.Random;

import static java.lang.Thread.sleep;

public class CO2 {
    private Random random = new Random();

    public double getCO2Level() {
        double co2 = random.nextDouble(71) + 30;
        System.out.println("CO2: "+co2);
        return co2;
    }
}
