package com.company;

public class Processo {
    private String nome;
    private int idProcesso;
    private double duracao;
    private boolean ativo;

    public Processo(String nome, int duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public boolean ativo(){
        return ativo;
    }

    public void ativar(){
        //mais coisa aqui possivelmente
        ativo = true;
    }
}
