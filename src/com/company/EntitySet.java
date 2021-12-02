package com.company;

import java.util.ArrayList;
import java.util.Random;

public class EntitySet {
    protected String name;
    protected int id;
    protected String mode;
    protected int size;
    protected int maxSize;
    protected ArrayList<Entity> entityList;
    protected boolean logging = false;
    protected ArrayList<Log> logs = new ArrayList<Log>();

    public EntitySet(String nome, int maxSize) {
        this.name = nome;
        this.mode = "FIFO"; //modo padrao
        this.size = 0;
        this.maxSize = maxSize;
        this.entityList = new ArrayList<Entity>();
    }

    public EntitySet(String nome) {
        this.name = nome;
        this.mode = "FIFO"; //modo padrao
        this.size = 0;
        this.maxSize = 2147483647;
        this.entityList = new ArrayList<Entity>();
    }

    public EntitySet() {
        this.mode = "FIFO"; //modo padrao
        this.size = 0;
        this.maxSize = 2147483647;
        this.entityList = new ArrayList<Entity>();
    }

    public void insert(Entity e, double time){
        if (entityList.size() < maxSize){
            entityList.add(e);
        }
        size = entityList.size();
        if (logging){
            logs.add(new Log(time, size));
        }
    }

    public Entity remove(double time){ //adicionar condicao if vazio
        if (entityList.size() < 1){
            return null;
        }
        Entity entityLeaving;
        switch (mode) {
            case "FIFO" -> {
                entityLeaving = entityList.get(0);
                entityList.remove(0);
            }
            case "LIFO" -> {
                entityLeaving = entityList.get(entityList.size() - 1);
                entityList.remove(entityList.size() - 1);
            }
            case "PRIORIDADE" -> {
                entityLeaving = entityList.get(0);
                int indiceMaiorPrioridade = 0;
                for (int i = 0; i < entityList.size(); i++) {
                    if (entityLeaving.getPriority() < entityList.get(i).getPriority()) {
                        entityLeaving = entityList.get(i);
                        indiceMaiorPrioridade = i;
                    }
                }
                entityList.remove(indiceMaiorPrioridade);
            }
            default -> {
                Random r = new Random();
                int indiceSorteado = r.nextInt(entityList.size());
                entityLeaving = entityList.get(indiceSorteado);
                entityList.remove(indiceSorteado);
            }
        }
        if (logging) {
            logs.add(new Log(time, size));
        }
        return entityLeaving;
    }

    public Entity removerPorId(int id, double time){
        if (size < 1){
            return null;
        }
        Entity entityLeaving;
        for (Entity e: entityList){
            if (id == e.getId()){
                entityLeaving = e;
                entityList.remove(e);
                if (logging) {
                    logs.add(new Log(time, size));
                }
                return entityLeaving;
            }
        }
        return null;
    }

    public boolean vazio(){
        return entityList.isEmpty();
    }

    public Entity procuraEntidade(int id){
        for (Entity e: entityList){
            if (e.getId() == id){
                return e;
            }
        }
        return null;
    }

    public int getSize(){
        return entityList.size();
    }

    public Entity getEntity(String name){
        for (Entity e: entityList){
            if (e.getName().equals(name)){
                return e;
            }
        }
        return null;
    }

    public boolean isFull(){
        return size >= maxSize;
    }

    public ArrayList<Entity> getEntityList() {
        return entityList;
    }

    // coleta de estatisticas
    public void startLog(){
        logging = true;
    }

    public void stopLog(){
        logging = false;
    }

    public double meanSize(){
        double totalSize = 0;
        for (Log l: logs){
            totalSize += l.getValue();
        }
        return totalSize / logs.size();
    }

    public double maxPossibleSize(){
        double maxSize = 0;
        for (Log l: logs){
            if (l.getValue() > maxSize){
                maxSize = l.getValue();
            }
        }
        return maxSize;
    }

    public ArrayList<Log> getLog(){
        return logs;
    }
}

