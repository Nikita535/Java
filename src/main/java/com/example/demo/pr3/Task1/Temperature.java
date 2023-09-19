package com.example.demo.pr3.Task1;


import java.util.Random;

import static java.lang.Thread.sleep;

public class Temperature  {
    private Random random = new Random();

    public int getTemperature() {
        int temp =  random.nextInt(16) + 15;
        System.out.println("Temperature: "+temp);
        return temp;
    }
}
