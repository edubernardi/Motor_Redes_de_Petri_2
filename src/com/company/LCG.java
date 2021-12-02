package com.company;

public class LCG {
    private int seed;

    public LCG(int seed) {
        this.seed = seed;
    }

    public double generateNext(){
        int module = 19993;
        int a = 15005;
        int b = 8371;

        seed = (a * seed + b) % module;
        return seed / (double) module;
    }
}
