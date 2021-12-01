package com.company;

public class Evento {
    protected String nome;
    protected int idEvento;
    protected double tec;

    public Evento(double tec) {
        this.tec = tec;
    }

    public Evento() {
        this.tec = 0;
    }

    public Evento(String nome) {
        this.nome = nome;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void execute(double time, Scheduler s){

    }

    public void scheduleNext(double tec, Scheduler s){

    }

    public double getTec() {
        return tec;
    }
}

