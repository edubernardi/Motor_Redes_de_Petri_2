package com.company;

import java.util.ArrayList;

public class Subrede extends Rede {
    private ArrayList<Arco> entradas = new ArrayList<Arco>();
    private ArrayList<Arco> saidas = new ArrayList<Arco>();
    private String label;

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

    public void executarCiclos() {
        System.out.println("Execução Subrede " + label);
        System.out.println("Estado inicial");
        mostraLugares();
        boolean existemHabilitadas = true;
        for (int ciclo = 0; existemHabilitadas; ciclo++){
            //scan de todas as transições
            existemHabilitadas = true;
            ArrayList<Transicao> habilitadas = new ArrayList<>();
            for (Transicao t: transicoes){
                if (t.ehSubRede()){
                    t.executarCiclosSubRede();
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
                System.out.println("\nFim da execução, não existem transições habilitadas");
                break;
            }
            //identifica concorrencias
            for (Lugar l: lugares) {
                if (l.getTokensInteressados() > l.getTokens()) {
                    ArrayList<Transicao> transicoesInteressadas = l.getTransicoesInteressadas();
                    System.out.println("Concorrencia identificada, escolha uma transição para ativar:");

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
            System.out.println("Após ciclo " + ciclo);
            mostraLugares();
        }
    }
}
