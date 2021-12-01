package com.company;

public class Entity {
    protected String name;
    protected int id;
    protected double creationTime;
    protected int priority;
    protected Rede redePetri;

    public Entity(int id) {
        this.id = id;
    }

    public Entity(Rede redePetri){
        this.redePetri = redePetri;
    }

    public Entity(String name) {
        this.name = name;
    }

    public Entity(String name, Rede redePetri) {
        this.name = name;
        this.redePetri = redePetri;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
