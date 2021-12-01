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
            System.out.printf("%.2f", time);
            System.out.println(": Grupo " + client.getId() + " terminou de pagar");
            int size = ((Client) client).getSize();
            if (size == 1) {
                if (s.getEntityGroup(0).isFull()){
                    System.out.printf("%.2f", time);
                    System.out.println(": Grupo " + client.getId() + " foi para a fila do balcao");
                    s.getEntityGroup(3).insert(client);
                } else {
                    System.out.printf("%.2f", time);
                    System.out.println(": Grupo " + client.getId() + " sentou-se no balcao");
                    s.getEntityGroup(0).insert(client);
                }
            } else if (size == 2) {
                if (s.getEntityGroup(1).isFull()){
                    System.out.printf("%.2f", time);
                    System.out.println(": Grupo " + client.getId() + " foi para a fila das mesas de 2 lugares");
                    s.getEntityGroup(4).insert(client);
                } else {
                    System.out.printf("%.2f", time);
                    System.out.println(": Grupo " + client.getId() + " sentou-se em uma mesa de 2 lugares balcao");
                    s.getEntityGroup(1).insert(client);
                }
            } else if (size > 2) {
                if (s.getEntityGroup(2).isFull()){
                    System.out.printf("%.2f", time);
                    System.out.println(": Grupo " + client.getId() + " foi para a fila das mesas de 4 lugares");
                    s.getEntityGroup(5).insert(client);
                } else {
                    System.out.printf("%.2f", time);
                    System.out.println(": Grupo " + client.getId() + " sentou-se em uma mesa de 4 lugares balcao");
                    s.getEntityGroup(2).insert(client);
                }
            }
            FEL filaEventos = s.getFilaEventos();
            FEL filaPedidos = new FEL();
            for (Evento e: filaEventos.getEventList()){
                if (e instanceof Pedido){
                    filaPedidos.addEvent(e);
                }
            }
            double nextTec = 0;
            if (filaPedidos.getSize() > 2){
                Evento next = filaPedidos.peekNext();
                nextTec += next.getTec();
            }
            s.adicionarEvent(new Pedido(tec + nextTec + s.normal(14, 5), client.getId()));
            System.out.println("Pedido " + client.getId() + " encaminhado para cozinha");
        }
    }

    public void scheduleNext(double tec, Scheduler s){
        if (!fila.vazio()){
            double n = (s.normal(8,3));
            s.adicionarEvent(new IniciarPedido(tec + n, fila));
        }
    }
}
