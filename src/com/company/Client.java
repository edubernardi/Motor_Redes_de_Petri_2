package com.company;

import java.util.Random;

public class Client extends Entity {
    private int size;
    private int id;

    public Client(int id) {
        super(id);
        Random r = new Random();
        this.size = r.nextInt(4) + 1;
    }

    public Client(String nome) {
        super(nome);
    }

    public int getSize() {
        return size;
    }
}
