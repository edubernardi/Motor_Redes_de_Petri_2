package com.company;

import java.util.ArrayList;

public class Transicao {
    private String label;
    private ArrayList<Arco> entradas = new ArrayList<Arco>();
    private ArrayList<Arco> saidas = new ArrayList<Arco>();
    private boolean ehSubRede;
    private Subrede subrede;
    private boolean resulucaoConcorrenciaAutomatica = true;

    public Transicao(String label) {
        this.label = label;
        ehSubRede = false;
    }

    public void adicionarEntrada(Arco a) {
        if (ehSubRede) {
            subrede.adicionarLugar((Lugar) a.getOrigem());
        } else {
            entradas.add(a);
        }
    }

    public boolean existemHabilitadas(boolean resulucaoConcorrenciaAutomatica) {
        this.resulucaoConcorrenciaAutomatica = resulucaoConcorrenciaAutomatica;
        return subrede.existemHabilitadas(resulucaoConcorrenciaAutomatica);
    }

    public void adicionarSaida(Arco a) {
        if (ehSubRede) {
            subrede.adicionarLugar((Lugar) a.getOrigem());
        } else {
            saidas.add(a);
        }
    }

    public String getLabel() {
        return label;
    }

    public boolean habilitada() {
        if (entradas.isEmpty() || saidas.isEmpty()) {
            return false;
        }
        boolean habilitada = true;
        for (Arco e : entradas) {
            Lugar origem = (Lugar) e.getOrigem();
            if (!e.ehInibidor()) {
                if (origem.getTokens() < e.getPeso()) {
                    habilitada = false;
                    break;
                }
            } else {
                if (origem.getTokens() >= e.getPeso()) {
                    habilitada = false;
                    break;
                }
            }
        }
        if (habilitada) {
            for (Arco e : entradas) {
                if (!e.ehInibidor()) {
                    Lugar origem = (Lugar) e.getOrigem();
                    origem.addTokensInteressados(e.getPeso());
                    origem.addTransicaoInteressada(this);
                }
            }
        }
        return habilitada;
    }

    public void disparar() {
        for (Arco a : entradas) {
            if (!a.ehInibidor()) {
                Lugar origem = (Lugar) a.getOrigem();
                if (a.ehReset()) {
                    origem.clear();
                } else {
                    origem.reduzirTokens(a.getPeso());
                }
            }
        }
        for (Arco a : saidas) {
            Lugar alvo = (Lugar) a.getAlvo();
            alvo.adicionarTokens(a.getPeso());
        }
    }

    public boolean ehSubRede() {
        return ehSubRede;
    }

    public void transformarEmSubRede() {
        ehSubRede = true;
        subrede = new Subrede(entradas, saidas, label);
        subrede.adicionarTransicao("T1");
        for (Arco a : entradas) {
            subrede.adicionarConexao(((Lugar) a.getOrigem()).getLabel(), "T1", a.getPeso());
        }
        for (Arco a : saidas) {
            subrede.adicionarConexao("T1", ((Lugar) a.getAlvo()).getLabel(), a.getPeso());
        }

    }

    public void executarCicloSubRede() {
        if (subrede != null) {
            System.out.println("\nIn??cio execu????o SubRede " + label);
            subrede.executarCiclos(resulucaoConcorrenciaAutomatica);
            System.out.println("Fim execu????o SubRede " + label + "\n");
        }
    }

    public void limpaConexoes() {
        entradas.clear();
        saidas.clear();
    }

    public Subrede getSubrede() {
        if (ehSubRede) {
            return subrede;
        }
        return null;
    }
}

