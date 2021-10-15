package com.company;

import java.util.ArrayList;

public class Lugar {
    private String label;
    private int tokens;
    private int tokensInteressados; //utilizado para identificar concorrencia
    private ArrayList<Transicao> transicoesInteressadas = new ArrayList<>();

    public Lugar(String label) {
        this.label = label;
        this.tokens = 0;
        this.tokensInteressados = 0;
    }

    public Lugar(String label, int tokens) {
        this.label = label;
        this.tokens = tokens;
        this.tokensInteressados = 0;
    }

    public String getLabel() {
        return label;
    }

    public int getTokens() {
        return tokens;
    }

    public void addTokensInteressados(int i) {
        tokensInteressados += i;
    }

    public void resetTokensInteressados() {
        tokensInteressados = 0;
    }

    public void addTransicaoInteressada(Transicao t) {
        transicoesInteressadas.add(t);
    }

    public void resetTransicoesInteressadas() {
        transicoesInteressadas.clear();
    }

    public int getTokensInteressados() {
        return tokensInteressados;
    }

    public ArrayList<Transicao> getTransicoesInteressadas() {
        return transicoesInteressadas;
    }

    public void reduzirTokens(int i) {
        this.tokens -= i;
        if (tokens < 0) {
            System.out.println("Erro, tokens negativos em " + label);
        }
    }

    public void adicionarTokens(int i) {
        if ((tokens + i) > 0) {
            this.tokens += i;
        }
    }

    public void clear() {
        this.tokens = 0;
    }

    public void setTokens(int tokens) {
        if (tokens > 0) {
            this.tokens = tokens;
        }
    }
}
