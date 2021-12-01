package com.company;

import jdk.jfr.Event;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Scheduler {
    private double tempo;
    private FEL filaEventos = new FEL();
    private LCG gerador = new LCG(17);
    private ArrayList<grupoEntidades> grupos = new ArrayList<grupoEntidades>();
    private int id = 0;

    public Scheduler() {
        this.tempo = 0;
    }

    public double getTempo() {
        return tempo;
    }

    public void criarEvento(String nome){
        Evento novoEvento = new Evento(nome);
        novoEvento.setIdEvento(id);
        id++;
        filaEventos.addEvent(novoEvento);
    }

    public void adicionarEvent(Evento e){
        e.setIdEvento(id);
        id++;
        filaEventos.addEvent(e);
    }

    public double exponencial(double media){
        return - media * Math.log(1 - gerador.gerarProximo());
    }

    public double normal(double media, double desvio){
        //aplicando metodo de Box-Muller
        double normalPadronizada = Math.sqrt(-2 * Math.log(gerador.gerarProximo())) * Math.cos(2 * Math.PI * gerador.gerarProximo());
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
        while (tempo < tempoMaximo){
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

    public void addGroup(grupoEntidades group){
        grupos.add(group);
    }

    public grupoEntidades getEntityGroup(int i){
        return grupos.get(i);
    }

    public FEL getFilaEventos() {
        return filaEventos;
    }

    public ArrayList<grupoEntidades> getGrupos() {
        return grupos;
    }
}

