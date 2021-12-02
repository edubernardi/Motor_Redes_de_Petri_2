package com.company;

public class Process {
    private String name;
    private int processId;
    private double duration;
    private boolean active;

    public Process(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public boolean ativo(){
        return active;
    }

    public void ativar(){
        active = true;
    }
}
