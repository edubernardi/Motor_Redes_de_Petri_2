package com.company;

import java.util.ArrayList;

public class Scheduler {
    private double tempo;
    private FEL filaEventos = new FEL();
    private LCG gerador = new LCG(17);
    private ArrayList<EntitySet> grupos = new ArrayList<EntitySet>();
    private ArrayList<Entity> entidades = new ArrayList<Entity>();
    private int id = 0;

    public Scheduler() {
        this.tempo = 0;
    }

    public double getTempo() {
        return tempo;
    }

    public void criarEvento(String nome){
        Evento novoEvento = new Evento(nome);
        //novoEvento.setIdEvento(id);
        //id++;
        filaEventos.addEvent(novoEvento);
    }

    public void adicionarEvent(Evento e){
        filaEventos.addEvent(e);
    }

    public double exponencial(double media){
        return - media * Math.log(1 - gerador.generateNext());
    }

    public double normal(double media, double desvio){
        //aplicando metodo de Box-Muller
        double normalPadronizada = Math.sqrt(-2 * Math.log(gerador.generateNext())) * Math.cos(2 * Math.PI * gerador.generateNext());
        //aplicando transformacoes de media e variancia e retornando
        return media + (desvio * normalPadronizada);
    }

    public void executar(){
        while (!filaEventos.isEmpty()){
            Evento eventoAtual = filaEventos.getNext();
            eventoAtual.execute(tempo, this);
            tempo = eventoAtual.getTec();
            eventoAtual.scheduleNext(tempo, this);
        }
    }

    public void executar(int tempoMaximo){
        while (tempo < tempoMaximo && !filaEventos.isEmpty()){
            Evento eventoAtual = filaEventos.getNext();
            eventoAtual.execute(tempo, this);
            tempo = eventoAtual.getTec();
            eventoAtual.scheduleNext(tempo, this);
        }
    }

    public int getId(){
        id++;
        return id;
    }

    public void addGroup(EntitySet group){
        grupos.add(group);
    }

    public EntitySet getEntityGroup(int i){
        return grupos.get(i);
    }

    public FEL getFilaEventos() {
        return filaEventos;
    }

    public ArrayList<EntitySet> getGrupos() {
        return grupos;
    }

    public void addEntity(Entity e){
        entidades.add(e);
    }

    public ArrayList<Entity> getEntidades(){
        return entidades;
    }

    public double random(){
        return gerador.generateNext();
    }

    public int getEntityTotal(){
        return entidades.size();
    }
}

