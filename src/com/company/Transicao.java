package com.company;

import java.util.ArrayList;

public class Transicao {
    private String label;
    private ArrayList<Arco> entradas = new ArrayList<Arco>();
    private ArrayList<Arco> saidas = new ArrayList<Arco>();
    private boolean ehSubRede;
    private Subrede subrede;

    public Transicao(String label) {
        this.label = label;
        ehSubRede = false;
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

    public boolean ehSubRede() {
        return ehSubRede;
    }

    public void transformarEmSubRede(){
        ehSubRede = true;
        subrede = new Subrede(entradas, saidas, label);
        subrede.adicionarTransicao("T1");
        for (Arco a: entradas){
            subrede.adicionarConexao(((Lugar) a.getOrigem()).getLabel(), "T1", a.getPeso());
        }
        for (Arco a: saidas){
            subrede.adicionarConexao("T1", ((Lugar) a.getAlvo()).getLabel(), a.getPeso());
        }

    }

    public void executarCiclosSubRede(){
        if (subrede != null) {
            subrede.executarCiclos();
        }
    }
}

