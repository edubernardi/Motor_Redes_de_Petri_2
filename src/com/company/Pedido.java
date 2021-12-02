package com.company;

import java.util.ArrayList;

public class Pedido extends Evento {
    private int clientId;

    public Pedido(double tec, int clientId) {
        super(tec);
        this.clientId = clientId;
    }

    public void execute(double time, Scheduler s){
        System.out.printf("%.2f", time);
        System.out.println(": Pedido " + clientId + " ficou pronto");

        s.adicionarEvent(new ServindoPedido(tec + 2, clientId));
    }
}

