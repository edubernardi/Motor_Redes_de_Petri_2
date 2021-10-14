package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Rede {
    protected ArrayList<Arco> arcos = new ArrayList<>();
    protected ArrayList<Transicao> transicoes = new ArrayList<>();
    protected ArrayList<Lugar> lugares = new ArrayList<>();
    protected Teclado t = new Teclado();

    public Rede() {

    }

    public void adicionarLugar(String label, int tokens){
        lugares.add(new Lugar(label, tokens));
    }

    public void adicionarLugar(String label){
        lugares.add(new Lugar(label, 0));
    }

    public void adicionarLugar(Lugar lugar){
        lugares.add(lugar);
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
                    t.adicionarSaida(a);
                    return;
                }
            } else if (destinoLabel.equals(t.getLabel())){
                destino = t;
                if (origem != null){
                    Arco a = new Arco(origem, destino,1, false, false);
                    t.adicionarEntrada(a);
                    return;
                }
            }
        }
        System.out.println("Erro ao gerar conexao"  + " " + origemLabel + "-" + destinoLabel);
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
                    t.adicionarSaida(a);
                    return;
                }
            } else if (destinoLabel.equals(t.getLabel())){
                destino = t;
                if (origem != null){
                    Arco a = new Arco(origem, destino, peso, false, false);
                    t.adicionarEntrada(a);
                    return;
                }
            }
        }
        System.out.println("Erro ao gerar conexao"  + " " + origemLabel + "-" + destinoLabel);
    }

    public void adicionarConexao(String origemLabel, String destinoLabel, int peso, boolean ehInibidor, boolean ehReset){
        Object origem = null;
        Object destino = null;
        if (ehInibidor && ehReset){
            System.out.println("Erro ao gerar conexao"  + " " + origemLabel + "-" + destinoLabel);
        } else{
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
                    if (destino != null) {
                        Arco a = new Arco(origem, destino, peso, ehInibidor, ehReset);
                        t.adicionarSaida(a);
                        return;
                    }
                } else if (destinoLabel.equals(t.getLabel())){
                    destino = t;
                    if (origem != null){
                        Arco a = new Arco(origem, destino, peso, ehInibidor, ehReset);
                        t.adicionarEntrada(a);
                        return;
                    }
                }
            }
            System.out.println("Erro ao gerar conexao"  + " " + origemLabel + "-" + destinoLabel);
        }
    }

    public void executarCiclos(boolean resulucaoConcorrenciaAutomatica){
        System.out.println("\nCiclo 0 (Estado inicial)");
        boolean existemHabilitadas = true;
        for (int ciclo = 1; existemHabilitadas; ciclo++){
            //scan de todas as transições
            existemHabilitadas = true;
            ArrayList<Transicao> habilitadas = new ArrayList<>();
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
                existemHabilitadas = false;
                mostraRede(habilitadas);
                System.out.println("Fim da execução, não existem transições habilitadas");
                break;
            }
            //mostra status rede
            mostraRede(habilitadas);
            //identifica concorrencias
            for (Lugar l: lugares) {
                if (l.getTokensInteressados() > l.getTokens() && l.getTokensInteressados() != 0) {
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
            for (Lugar lugar: lugares){
                lugar.resetTokensInteressados();
                lugar.resetTransicoesInteressadas();
            }
            //pede autorização do usuario para continuar
            t.leString("Aperte ENTER para continuar a simulação");
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
                if (!t.ehSubRede()){
                    if (t.habilitada()){
                        t.disparar();
                    }
                }
            }
            //prints
            System.out.println("Ciclo " + ciclo);
            for (Lugar lugar: lugares){
                lugar.resetTokensInteressados();
                lugar.resetTransicoesInteressadas();
            }
            //mostraRede(habilitadas);
        }
    }

    public void mostraLugares() {
        for (Lugar lugar : lugares) {
            lugar.resetTokensInteressados(); //aproveita o loop para resetar o valor
            lugar.resetTransicoesInteressadas();
            System.out.println("Lugar " + lugar.getLabel() + ": " + lugar.getTokens() + " tokens");
        }
    }

    public void mostraTransicoes(ArrayList<Transicao> habilitadas){
        int i = 0;
        for (Transicao t: transicoes){
            if (t.ehSubRede()){
                System.out.println("Transicao " + t.getLabel() + " eh Subrede");
            } else {
                if (habilitadas.contains(t)){
                    System.out.println("Transicao " + t.getLabel() + " habilitada");
                }
                else {
                    System.out.println("Transicao " + t.getLabel() + " nao habilitada");
                }
            }
        }
    }

    public void transicaoParaSubRede(String label){
        for (Transicao t: transicoes){
            if (t.getLabel().equals(label)){
                t.transformarEmSubRede();
            }
        }
    }

    public void limpaConexoes(String label){
        for (Transicao t: transicoes){
            if (t.getLabel().equals(label)) {
                t.limpaConexoes();
            }
        }
    }

    public void removeLugar(String label){
        lugares.removeIf(l -> l.getLabel().equals(label));
    }

    public void removeTransicao(String label){
        transicoes.removeIf(l -> l.getLabel().equals(label));
    }

    public void adicionaTokens(String label, int i){
        for (Lugar l: lugares){
            if (l.getLabel().equals(label)){
                l.adicionarTokens(i);
            }
        }
    }

    public void removeTokens(String label, int i){
        for (Lugar l: lugares){
            if (l.getLabel().equals(label)){
                l.adicionarTokens(-i);
            }
        }
    }

    public void setTokens(String label, int i){
        for (Lugar l: lugares){
            if (l.getLabel().equals(label)){
                l.setTokens(i);
            }
        }
    }

    public Transicao getTransicao(String label){
        for (Transicao t: transicoes){
            if (t.getLabel().equals(label)){
                return t;
            }
        }
        return null;
    }

    public Lugar getLugar(String label){
        for (Lugar l: lugares){
            if (l.getLabel().equals(label)){
                return l;
            }
        }
        return null;
    }

    public void mostraRede(ArrayList<Transicao> habilitadas){
        System.out.print("| ");
        for (Lugar l: lugares){
            System.out.print(l.getLabel() + " | ");
        }
        for (Transicao t: transicoes){
            System.out.print(t.getLabel() + " | ");
        }
        System.out.print("\n| ");
        for (Lugar l: lugares) {
            System.out.print(l.getTokens() + "  | ");
        }
        for (Transicao t: transicoes){
            if (t.ehSubRede()){
                System.out.print("SR" + " | ");
            } else {
                if (habilitadas.contains(t)){
                    System.out.print("SIM" + "| ");
                }
                else {
                    System.out.print("NÃO" + "| ");
                }
            }
        }
        System.out.print("\n");
    }
}
