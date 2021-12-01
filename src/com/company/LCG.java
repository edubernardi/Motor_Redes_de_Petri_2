package com.company;

public class LCG {
    private int semente;

    public LCG(int semente) {
        this.semente = semente;
    }

    public double gerarProximo(){
        int modulo = 100;
        int a = 17;
        int b = 43;

        semente = (a * semente + b) % modulo;
        return semente / (double) 100;
    }
}
