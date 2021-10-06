package com.company;

import java.util.ArrayList;

public class Transicao {
    private String label;
    private ArrayList<Arco> entradas = new ArrayList<Arco>();
    private ArrayList<Arco> saidas = new ArrayList<Arco>();

    public Transicao(String label) {
        this.label = label;
    }

    public void adicionaEntrada(Arco a){
        entradas.add(a);
    }

    public void adicionaSaida(Arco a){
        saidas.add(a);
    }

    public String getLabel() {
        return label;
    }

    public boolean habilitada(){
        boolean habilitada = true;
        for (Arco e: entradas){
            Lugar origem = (Lugar) e.getOrigem();
            if (!e.ehInibidor()){
                if (origem.getTokens() < e.getPeso()){
                    habilitada = false;
                    break;
                }
            }
            else {
                if (origem.getTokens() >= e.getPeso()){
                    habilitada = false;
                    break;
                }
            }
        }
        if (habilitada){
            for (Arco e: entradas){
                if (!e.ehInibidor()){
                    Lugar origem = (Lugar)  e.getOrigem();
                    origem.addTokensInteressados(e.getPeso());
                    origem.addTransicaoInteressada(this);
                }
            }
        }
        return habilitada;
    }

    public void disparar(){
        for (Arco a: entradas){
            if (!a.ehInibidor()){
                Lugar origem = (Lugar) a.getOrigem();
                if (a.ehReset()){
                    origem.clear();
                } else {
                    origem.reduzirTokens(a.getPeso());
                }
            }
        }
        for (Arco a: saidas){
            Lugar alvo = (Lugar) a.getAlvo();
            alvo.adicionarTokens(a.getPeso());
        }
    }
}
