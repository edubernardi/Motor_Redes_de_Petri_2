package com.company;

import java.util.Random;

public class Client extends Entity {
    private int size;

    public Client(int id, Scheduler s) {
        super(id);
        double random = s.random();
        this.size = (((int) (random * 100)) % 4) + 1;
    }

    public Client(String nome) {
        super(nome);
    }

    public int getSize() {
        return size;
    }
}
