package com.company;

public class LCG {
    private int semente;

    public LCG(int semente) {
        this.semente = semente;
    }

    public double gerarProximo(){
        int modulo = 19993;
        int a = 15005;
        int b = 8371;

        semente = (a * semente + b) % modulo;
        return semente / (double) modulo;
    }
}
