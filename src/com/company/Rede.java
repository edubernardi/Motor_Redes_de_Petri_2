package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Rede {
    private ArrayList<Arco> arcos = new ArrayList<>();
    private ArrayList<Transicao> transicoes = new ArrayList<>();
    private ArrayList<Lugar> lugares = new ArrayList<>();
    Teclado t = new Teclado();

    public Rede() {

    }

    public void adicionarLugar(String label, int tokens){
        lugares.add(new Lugar(label, tokens));
    }

    public void adicionarLugar(String label){
        lugares.add(new Lugar(label, 0));
    }

    public void adicionarTransicao(String label){
        transicoes.add(new Transicao(label));
    }

    public void adicionarConexao(String origemLabel, String destinoLabel){
        Object origem = null;
        Object destino = null;
        for (Lugar l: lugares){
            if (origemLabel.equals(l.getLabel())){
                origem = l;
            } else if (destinoLabel.equals(l.getLabel())){
                destino = l;
            }
        }

        for (Transicao t: transicoes){
            if (origemLabel.equals(t.getLabel())){
                origem = t;
                if (destino != null){
                    Arco a = new Arco(origem, destino, 1, false, false);
                    t.adicionaSaida(a);
                    return;
                }
            } else if (destinoLabel.equals(t.getLabel())){
                destino = t;
                if (origem != null){
                    Arco a = new Arco(origem, destino,1, false, false);
                    t.adicionaEntrada(a);
                    return;
                }
            }
        }
        System.out.println("Erro ao gerar conexao"  + " " + origemLabel + " " + destinoLabel);
    }

    public void adicionarConexao(String origemLabel, String destinoLabel, int peso){
        Object origem = null;
        Object destino = null;
        for (Lugar l: lugares){
            if (origemLabel.equals(l.getLabel())){
                origem = l;
            } else if (destinoLabel.equals(l.getLabel())){
                destino = l;
            }
        }

        for (Transicao t: transicoes){
            if (origemLabel.equals(t.getLabel())){
                origem = t;
                if (destino != null){
                    Arco a = new Arco(origem, destino, peso, false, false);
                    t.adicionaSaida(a);
                    return;
                }
            } else if (destinoLabel.equals(t.getLabel())){
                destino = t;
                if (origem != null){
                    Arco a = new Arco(origem, destino, peso, false, false);
                    t.adicionaEntrada(a);
                    return;
                }
            }
        }
        System.out.println("Erro ao gerar conexao"  + " " + origemLabel + " " + destinoLabel);
    }

    public void adicionarConexao(String origemLabel, String destinoLabel, int peso, boolean ehInibidor, boolean ehReset){
        Object origem = null;
        Object destino = null;
        for (Lugar l: lugares){
            if (origemLabel.equals(l.getLabel())){
                origem = l;
            } else if (destinoLabel.equals(l.getLabel())){
                destino = l;
            }
        }

        for (Transicao t: transicoes){
            if (origemLabel.equals(t.getLabel())){
                origem = t;
                if (destino != null){
                    Arco a = new Arco(origem, destino, peso, ehInibidor, ehReset);
                    t.adicionaSaida(a);
                    return;
                }
            } else if (destinoLabel.equals(t.getLabel())){
                destino = t;
                if (origem != null){
                    Arco a = new Arco(origem, destino, peso, ehInibidor, ehReset);
                    t.adicionaEntrada(a);
                    return;
                }
            }
        }
        System.out.println("Erro ao gerar conexao"  + " " + origemLabel + " " + destinoLabel);
    }

    public void executarCiclos(int ciclos){
        System.out.println("Estado inicial");
        mostraLugares();
        for (int ciclo = 0; ciclo < ciclos; ciclo++){
            //scan de todas as transições
            ArrayList<Transicao> habilitadas = new ArrayList<>();
            for (Transicao t: transicoes){
                if (t.habilitada()){
                    habilitadas.add(t);
                }
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

    public void mostraLugares() {
        int i = 0;
        for (Lugar lugar : lugares) {
            lugar.resetTokensInteressados(); //aproveita o loop para resetar o valor
            lugar.resetTransicoesInteressadas();
            System.out.println("    Lugar " + lugar.getLabel() + ": " + lugar.getTokens() + " tokens");
            i++;
        }
        System.out.println("\n");
    }
}
