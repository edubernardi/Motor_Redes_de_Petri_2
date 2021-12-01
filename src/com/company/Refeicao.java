package com.company;

import java.util.ArrayList;

public class Refeicao extends Evento {
    private int clientId;

    public Refeicao(double tec, int id) {
        super(tec);
        this.clientId = id;
    }

    public void execute(double time, Scheduler s){
        ArrayList<grupoEntidades> assentos = s.getGrupos();
        for (int i = 0; i < 3; i++){
            ArrayList<Entity> clients = assentos.get(i).getLista();
            for (int j = 0; j < clients.size(); j++){
                if (clients.get(j).getId() == clientId){
                    System.out.printf("%.2f", time);
                    System.out.println(": Grupo " + clientId + " concluiu a refeicao");

                    clients.remove(j);
                    Entity nextClient = assentos.get(i + 3).remove();
                    if (nextClient != null){
                        clients.add(nextClient);
                        System.out.printf("%.2f", time);
                        System.out.println(": Grupo " + nextClient.getId() + " sentou-se no lugar de " + clientId);
                    }
                    i = 3;
                    break;
                }
            }
        }
    }
}
