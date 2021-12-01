package com.company;

public class IniciarPedido extends Evento {
    private grupoEntidades fila;

    public IniciarPedido(double tec, grupoEntidades fila) {
        super(tec);
        this.fila = fila;
    }

    public void execute(double time, Scheduler s) {
        if (!fila.vazio()){
            Entity client = fila.remove();
            System.out.println("Grupo " + client.getId() + " comecou a pagar");
        }
    }

    public void scheduleNext(double tec, Scheduler s){
        if (!fila.vazio()){
            double n = (s.normal(8,3));
            s.adicionarEvent(new IniciarPedido(tec + n, fila));
        }
    }
}
