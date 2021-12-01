package com.company;

public class Chegada extends Evento {
    private double timeLimit;
    private grupoEntidades fila1;
    private grupoEntidades fila2;

    public Chegada(double tec, double timeLimit, grupoEntidades fila1, grupoEntidades fila2) {
        super(tec);
        this.timeLimit = timeLimit;
        this.fila1 = fila1;
        this.fila2 = fila2;
    }

    public Chegada(double tec, grupoEntidades fila1, grupoEntidades fila2) {
        super(tec);
        this.fila1 = fila1;
        this.fila2 = fila2;
    }

    public void execute(double time, Scheduler s) {
        Client newClient = new Client(s.getId());

        if (fila1.getSize() > fila2.getSize()){
            fila2.insert(newClient);
            System.out.println(time + ": Chegou novo grupo " + newClient.getId() + " de " + newClient.getSize() +
                    " clientes e foi para a Fila 2");
            IniciarPedido pedido = new IniciarPedido(tec + s.normal(8,3), fila2);
            s.adicionarEvent(pedido);
        } else {
            fila1.insert(newClient);
            System.out.println(time + ": Chegou novo grupo " + newClient.getId() + " de " + newClient.getSize() +
                    " clientes e foi para a Fila 1");
            IniciarPedido pedido = new IniciarPedido(tec + s.normal(8,3), fila1);
            s.adicionarEvent(pedido);
        }
    }

    public void scheduleNext(double tec, Scheduler s){
        if (tec < timeLimit){
            Chegada next = new Chegada(tec + (s.exponencial(3)), timeLimit,fila1, fila2);
            s.adicionarEvent(next);
        }
    }
}
