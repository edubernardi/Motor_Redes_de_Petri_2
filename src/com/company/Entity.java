package com.company;

public class Entity {
    private String name;
    private int id;
    private double creationTime;
    private int priority;
    private Rede redePetri;

    public Entity(int id) {
        this.id = id;
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
