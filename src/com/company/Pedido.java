package com.company;

import java.util.ArrayList;

public class Pedido extends Evento {
    private int clientId;

    public Pedido(double tec, int clientId) {
        super(tec);
        this.clientId = clientId;
    }

    public void execute(double time, Scheduler s){
        System.out.println(time + ": Pedido " + clientId + " ficou pronto");
        ArrayList<grupoEntidades> assentos = s.getGrupos();
        for (int i = 0; i < 3; i++){
            ArrayList<Entity> clients = assentos.get(i).getLista();
            for (int j = 0; j < clients.size(); j++){
                if (clients.get(j).getId() == clientId){
                    System.out.println(time + ": Grupo " + clientId + " iniciou a refeicao");
                    s.adicionarEvent(new Refeicao(tec + s.normal(20,8), clientId));
                    i = 3;
                    break;
                }
            }
        }
    }
}

