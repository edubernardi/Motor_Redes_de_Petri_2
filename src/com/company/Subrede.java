package com.company;

import java.util.ArrayList;
import java.util.Random;

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

    public boolean existemHabilitadas(boolean resulucaoConcorrenciaAutomatica){
        System.out.println("    Execução subrede");
        boolean existemHabilitadas = true;
        mostraLugares();
        habilitadas.clear();
        //scan de todas as transições
        for (Transicao t: transicoes){
            if (t.ehSubRede()){
                if (t.existemHabilitadas(resulucaoConcorrenciaAutomatica)){
                    habilitadas.add(t);
                }
            }
            else {
                if (t.habilitada()){
                    habilitadas.add(t);
                }
            }
        }
        //verifica se existem transições para executar
        if (habilitadas.isEmpty()){
            mostraTransicoes(habilitadas);
            System.out.println("Fim da execução, não existem transições habilitadas");
            return false;
        }
        //mostra transicoes
        mostraTransicoes(habilitadas);
        //identifica concorrencias
        for (Lugar l: lugares) {
            if (l.getTokensInteressados() > l.getTokens()) {
                ArrayList<Transicao> transicoesInteressadas = l.getTransicoesInteressadas();
                int escolha = -1;
                if (resulucaoConcorrenciaAutomatica){
                    Random r = new Random();
                    escolha = r.nextInt(transicoesInteressadas.size());
                    System.out.println("Concorrencia identificada, execução da transição " +
                            transicoesInteressadas.get(escolha).getLabel() + " escolhida de forma aleatória");
                } else {
                    System.out.println("Concorrencia identificada, escolha uma transição para ativar:");
                    while (escolha < 0 || escolha > transicoesInteressadas.size()) {
                        int i = 0;
                        for (Transicao t : transicoesInteressadas) {
                            System.out.println(i + " - " + t.getLabel());
                            i += 1;
                        }
                        escolha = t.leInt("Escolha (int): ");
                    }
                }
                transicoesInteressadas.remove(escolha);
                for (Transicao t : transicoesInteressadas) {
                    habilitadas.remove(t);
                }
            }
        }
        return true;
    }

    public void executarCiclos() {
        //dispara as transições habilitadas
        for(Transicao t: habilitadas){
            if (t.ehSubRede()){
                t.executarCicloSubRede();
            } else {
                t.disparar();
            }
        }
        //repetir enquanto possivel
        for (Transicao t: habilitadas){
            if (t.ehSubRede()){
                t.executarCicloSubRede();
            }
            else {
                if (t.habilitada()){
                    t.disparar();
                }
            }
        }
        //prints
        System.out.println("\nApós execução");
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
