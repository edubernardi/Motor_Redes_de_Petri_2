package com.company;

import java.util.ArrayList;

public class Refeicao extends Evento {
    private int clientId;

    public Refeicao(double tec, int id) {
        super(tec);
        this.clientId = id;
    }

    public void execute(double time, Scheduler s){
        System.out.printf("%.2f", time);
        System.out.println(": Grupo " + clientId + " concluiu a refeicao");
        s.adicionarEvent(new HigienizandoMesa(tec + 1, clientId));
    }
}

