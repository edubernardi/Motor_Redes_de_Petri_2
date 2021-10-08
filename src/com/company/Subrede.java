package com.company;

import java.util.ArrayList;

public class Subrede extends Rede {
    private ArrayList<Arco> entradas = new ArrayList<Arco>();
    private ArrayList<Arco> saidas = new ArrayList<Arco>();
    private String label;
    private ArrayList<Transicao> habilitadas = new ArrayList<>();

    public Subrede(ArrayList<Arco> entradas, ArrayList<Arco> saidas, String label) {
        this.entradas = entradas;
        this.saidas = saidas;
        this.label = label;
        for (Arco a: entradas){
            lugares.add((Lugar) a.getOrigem());
        }
        for (Arco a: saidas){
            lugares.add((Lugar) a.getAlvo());
        }
    }

    public boolean existemHabilitadas(){
        boolean existemHabilitadas = true;
        habilitadas.clear();
        //scan de todas as transições
        ArrayList<Transicao> habilitadas = new ArrayList<>();
        for (Transicao t: transicoes){
            if (t.ehSubRede()){
                t.executarCicloSubRede();
            }
            else {
                if (t.habilitada()){
                    habilitadas.add(t);
                }
            }
        }
        //verifica se existem transições para executar
        if (habilitadas.isEmpty()){
            existemHabilitadas = false;
            System.out.println("    Fim da execução da subrede " + label + ", não existem transições habilitadas\n");
        }
        return existemHabilitadas;
    }

    public void executarCiclos() {
        System.out.println("\n    Execução Subrede " + label);
        System.out.println("    Estado inicial");
        mostraLugares();
        //mostra transicoes
        mostraTransicoes(habilitadas);
        //identifica concorrencias
        for (Lugar l: lugares) {
            if (l.getTokensInteressados() > l.getTokens()) {
                ArrayList<Transicao> transicoesInteressadas = l.getTransicoesInteressadas();
                System.out.println("    Concorrencia identificada, escolha uma transição para ativar:");

                int escolha = -1;
                while (escolha < 0 || escolha > transicoesInteressadas.size()) {
                    int i = 0;
                    for (Transicao t : transicoesInteressadas) {
                        System.out.println(i + " - " + t.getLabel());
                        i += 1;
                    }
                    escolha = t.leInt("Escolha (int): ");
                }
                transicoesInteressadas.remove(escolha);
                for (Transicao t : transicoesInteressadas) {
                    habilitadas.remove(t);
                }
            }
        }
        //dispara as transições habilitadas
        for(Transicao t: habilitadas){
            t.disparar();
        }
        mostraLugares();

    }

    public void mostraLugares() {
        for (Lugar lugar : lugares) {
            lugar.resetTokensInteressados(); //aproveita o loop para resetar o valor
            lugar.resetTransicoesInteressadas();
            System.out.println("    Lugar " + lugar.getLabel() + ": " + lugar.getTokens() + " tokens");
        }
    }

    public void mostraTransicoes(ArrayList<Transicao> habilitadas){
        int i = 0;
        for (Transicao t: transicoes){
            if (habilitadas.contains(t)){
                System.out.println("    Transicao " + t.getLabel() + " habilitada");
            }
            else {
                System.out.println("    Transicao " + t.getLabel() + " nao habilitada");
            }
        }
    }

}
