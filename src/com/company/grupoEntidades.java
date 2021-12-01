package com.company;

import java.util.ArrayList;
import java.util.Random;

public class grupoEntidades {
    private String name;
    private int id;
    private String mode;
    private int size;
    private int maxSize;
    private ArrayList<Entity> lista;

    public grupoEntidades(String nome, int maxSize) {
        this.name = nome;
        this.mode = "FIFO"; //modo padrao
        this.size = 0;
        this.maxSize = maxSize;
        this.lista = new ArrayList<Entity>();
    }

    public grupoEntidades(String nome) {
        this.name = nome;
        this.mode = "FIFO"; //modo padrao
        this.size = 0;
        this.maxSize = 2147483647;
        this.lista = new ArrayList<Entity>();
    }

    public void insert(Entity e){
        if (lista.size() < maxSize){
            lista.add(e);
        }
        size = lista.size();
    }

    public Entity remove(){ //adicionar condicao if vazio
        if (lista.size() < 1){
            return null;
        }
        Entity entityLeaving;
        switch (mode) {
            case "FIFO" -> {
                entityLeaving = lista.get(0);
                lista.remove(0);
                return entityLeaving;
            }
            case "LIFO" -> {
                entityLeaving = lista.get(lista.size() - 1);
                lista.remove(lista.size() - 1);
                return entityLeaving;
            }
            case "PRIORIDADE" -> {
                entityLeaving = lista.get(0);
                int indiceMaiorPrioridade = 0;
                for (int i = 0; i < lista.size(); i++) {
                    if (entityLeaving.getPriority() < lista.get(i).getPriority()) {
                        entityLeaving = lista.get(i);
                        indiceMaiorPrioridade = i;
                    }
                }
                lista.remove(indiceMaiorPrioridade);
                return entityLeaving;
            }
            default -> {
                Random r = new Random();
                int indiceSorteado = r.nextInt(lista.size());
                entityLeaving = lista.get(indiceSorteado);
                lista.remove(indiceSorteado);
                return entityLeaving;
            }
        }
    }

    public Entity removerPorId(int id){
        if (size < 1){
            return null;
        }
        Entity entityLeaving = lista.get(0);
        for (Entity e: lista){
            if (id == e.getId()){
                entityLeaving = e;
                lista.remove(e);
                return entityLeaving;
            }
        }
        return null;
    }

    public boolean vazio(){
        return lista.isEmpty();
    }

    public Entity procuraEntidade(int id){
        for (Entity e: lista){
            if (e.getId() == id){
                return e;
            }
        }
        return null;
    }

    public int getSize(){
        return lista.size();
    }

    public Entity getEntity(String name){
        for (Entity e: lista){
            if (e.getName().equals(name)){
                return e;
            }
        }
        return null;
    }

    public boolean isFull(){
        return size >= maxSize;
    }

    public ArrayList<Entity> getLista() {
        return lista;
    }

    // coleta de estatisticas
    //public double tamanhoMedio(){

    //}


}

