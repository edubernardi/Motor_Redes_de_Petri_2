package com.company;

import java.util.ArrayList;

public class Pedido extends Evento {
    private int clientId;
    private boolean tryAgain;

    public Pedido(double tec, int clientId) {
        super(tec);
        this.clientId = clientId;
        tryAgain = false;
    }

    public Pedido(double tec, int clientId, boolean tryAgain) {
        super(tec);
        this.clientId = clientId;
        this.tryAgain = tryAgain;
    }

    public void execute(double time, Scheduler s){
        if (tryAgain){
            System.out.printf("%.2f", time);
            System.out.println(": Tentando entregar pedido ao grupo " + clientId);
        } else {
            System.out.printf("%.2f", time);
            System.out.println(": Pedido " + clientId + " ficou pronto");
        }
        ArrayList<grupoEntidades> assentos = s.getGrupos();
        boolean served = false;
        for (int i = 0; i < 3; i++){
            ArrayList<Entity> clients = assentos.get(i).getLista();
            for (int j = 0; j < clients.size(); j++){
                if (clients.get(j).getId() == clientId){
                    System.out.printf("%.2f", time);
                    System.out.println(": Grupo " + clientId + " iniciou a refeicao");
                    s.adicionarEvent(new Refeicao(tec + s.normal(20,8), clientId));
                    served = true;
                    i = 3;
                    break;
                }
            }
        }
        if (!served){
            s.adicionarEvent(new Pedido(time + 5, clientId, true));
            //quando o pedido fica pronto, mas o grupo que o pediu não pode realizar a refeiçaõ por não estar sentado,
            //é gerada um novo evento de tentativa de servir esse grupo, 5 minutos no futuro
        }
    }
}

