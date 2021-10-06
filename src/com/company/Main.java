package com.company;

public class Main {

    public static void main(String[] args) {
        Rede r = new Rede();
//        r.adicionarLugar("L1", 1);
//        r.adicionarTransicao("T1");
//        r.adicionaConexao("L1", "T1");
//
//        r.adicionarLugar("L2");
//        r.adicionaConexao("T1", "L2");
//
//        r.adicionarTransicao("T2");
//        r.adicionaConexao("L1", "T2");
//
//        r.adicionarLugar("L3");
//        r.adicionaConexao("T2", "L3");
//        r.executarCiclos(3);
        r.adicionarLugar("L1", 5);
        r.adicionarLugar("L2");
        r.adicionarLugar("L3" );

        r.adicionarTransicao("T1");
        r.adicionarTransicao("T2");

        r.adicionarConexao("L1","T1",2, false, false);
        r.adicionarConexao("T1","L2",3);
        r.adicionarConexao("L2","T2",5);
        r.adicionarConexao("T2","L3",1);

        r.transicaoParaSubRede("T1");

        r.executarCiclos();

    }
}

