package com.company;

public class Garcon extends Entity {
    public Garcon(Rede redePetri) {
        super(redePetri);
    }

    public void executar(){
        redePetri.executarCiclos(true);
    }

    public void executar(int i){
        redePetri.executarCiclos(true, i + 1);
    }

    public void adiconaMesaHigienizar(){
        redePetri.adicionaTokens("Mesas aguardando higienizacao", 1);
    }

    public void adiconaRefeicao(){
        redePetri.adicionaTokens("Refeicoes aguardando transporte", 1);
    }

    public void adiconaCaixaBanheiro(){
        redePetri.adicionaTokens("Caixa precisa ir ao banheiro", 1);
    }

    public Rede getRede(){
        return redePetri;
    }
}
