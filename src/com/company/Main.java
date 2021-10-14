package com.company;

public class Main {

    public static void main(String[] args) {
        Rede r = new Rede();
        //TESTE 1 - Concorrencia
        /*
        r.adicionarLugar("L1", 1);
        r.adicionarTransicao("T1");
        r.adicionarConexao("L1", "T1", 1);

        r.adicionarLugar("L2");
        r.adicionarConexao("T1", "L2");

        r.adicionarTransicao("T2");
        r.adicionarConexao("L1", "T2");

        r.adicionarLugar("L3");
        r.adicionarConexao("T2", "L3");
        r.executarCiclos(true);
        */

        /*
        //Teste 2- Subredes
        r.adicionarLugar("L1", 1);
        r.adicionarLugar("L2");
        r.adicionarLugar("L3" );

        r.adicionarTransicao("T1");
        r.adicionarTransicao("T2");

        r.adicionarConexao("L1","T1",2, false, false);
        r.adicionarConexao("T1","L2",3);
        r.adicionarConexao("L2","T2",5);
        r.adicionarConexao("T2","L3",1);
        r.adicionarConexao("L1", "T1");
        r.adicionarConexao("T1", "L2");
        r.adicionarConexao("T1", "L3");
        r.transicaoParaSubRede("T1");

        r.getTransicao("T1").getSubrede().adicionarTransicao("T2");
        r.getTransicao("T1").getSubrede().adicionarTransicao("T3");

        r.getTransicao("T1").getSubrede().adicionarConexao("L1","T2");
        r.getTransicao("T1").getSubrede().adicionarConexao("L1","T3");

        r.getTransicao("T1").getSubrede().adicionarConexao("T2","L2");
        r.getTransicao("T1").getSubrede().adicionarConexao("T2","L3");
        //r.adicionarConexao("L1","T1",2, false, false);
        //r.getTransicao("T1").getSubrede().adicionarConexao("L1", "T1", 2, false, false);
        r.executarCiclos(true);
        */

        ///*
        //Teste 3 - Do enunciado
        r.adicionarLugar("L1", 2);
        r.adicionarLugar("L2");
        r.adicionarLugar("L3", 2);
        r.adicionarLugar("L4");
        r.adicionarLugar("L5", 5);
        r.adicionarLugar("L6");
        r.adicionarLugar("L7");
        r.adicionarLugar("L8");

        r.adicionarTransicao("T1");
        r.adicionarTransicao("T2");
        r.adicionarTransicao("T3");
        r.adicionarTransicao("T4");

        r.adicionarConexao("L1","T1");
        r.adicionarConexao("T1","L2");
        r.adicionarConexao("L2","T2");
        r.adicionarConexao("T2","L4");
        r.adicionarConexao("L4","T3");

        r.adicionarConexao("T3","L6");
        r.adicionarConexao("L6","T4");
        r.adicionarConexao("T4","L8");

        r.adicionarConexao("T3","L3",2);
        r.adicionarConexao("L3","T2",2);

        r.adicionarConexao("T3", "L7");
        r.adicionarConexao("L7", "T4");
        r.adicionarConexao("T4", "L5", 3);
        r.adicionarConexao("L5", "T2", 3);

        r.executarCiclos(true);
        //*/



    }
}

