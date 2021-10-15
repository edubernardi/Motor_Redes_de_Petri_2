package com.company;

public class Arco {
    private Object origem;
    private Object alvo;
    private int peso;
    private boolean ehInibidor;
    private boolean ehReset;

    public Arco(Object origem, Object alvo, int peso, boolean ehInibidor, boolean ehReset) {
        this.origem = origem;
        this.alvo = alvo;
        this.peso = peso;
        this.ehInibidor = ehInibidor;
        this.ehReset = ehReset;
    }

    public int getPeso() {
        return peso;
    }

    public Object getOrigem() {
        return origem;
    }

    public Object getAlvo() {
        return alvo;
    }

    public boolean ehInibidor() {
        return ehInibidor;
    }

    public boolean ehReset() {
        return ehReset;
    }

    public void setOrigem(Object origem) {
        this.origem = origem;
    }

    public void setAlvo(Object alvo) {
        this.alvo = alvo;
    }
}
