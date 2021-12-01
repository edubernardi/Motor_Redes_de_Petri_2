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

        r.adicionarLugar("L4");
        r.adicionarTransicao("T3");
        r.adicionarConexao("L1", "T3");
        r.adicionarConexao("T3", "L4");

        r.adicionarLugar("L3");
        r.adicionarConexao("T2", "L3");
        r.executarCiclos(true);
        */

        /*
        //Teste 2- Subredes
        r.adicionarLugar("L1", 1);
        r.adicionarLugar("L2");
        r.adicionarLugar("L3");

        r.adicionarTransicao("T1");

        r.adicionarConexao("L1", "T1");
        r.adicionarConexao("T1", "L2");
        r.adicionarConexao("T1", "L3");

        r.transicaoParaSubRede("T1");

        r.getTransicao("T1").getSubrede().limpaConexoes("T1");
        r.getTransicao("T1").getSubrede().adicionarTransicao("T2");

        r.getTransicao("T1").getSubrede().adicionarConexao("L1", "T1");
        r.getTransicao("T1").getSubrede().adicionarConexao("L1", "T2");

        //teste extra
        //r.getTransicao("T1").getSubrede().adicionarLugar("L4");
        //r.getTransicao("T1").getSubrede().adicionarConexao("T1","L4");
        //r.getTransicao("T1").getSubrede().adicionarConexao("L4","T2");
        //r.getTransicao("T1").getSubrede().adicionarConexao("T2","L3");
        r.getTransicao("T1").getSubrede().adicionarConexao("T1", "L2");
        r.getTransicao("T1").getSubrede().adicionarConexao("T2", "L3");

        r.executarCiclos(true);
        */

        /*
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


        r.adicionarLugar("L1");
        r.adicionarLugar("L2", 2);
        r.adicionarLugar("L3", 1);
        r.adicionarLugar("L4");
        r.adicionarLugar("L5");
        r.adicionarLugar("L6");
        r.adicionarLugar("L7", 10);
        r.adicionarLugar("L8");
        r.adicionarLugar("L9");
        r.adicionarLugar("L10");
        r.adicionarLugar("L11");
        r.adicionarLugar("L12");
        r.adicionarLugar("L13");

        r.adicionarTransicao("Ta");
        r.adicionarTransicao("Tb");
        r.adicionarTransicao("Tc");
        r.adicionarTransicao("Td");
        r.adicionarTransicao("Te");
        r.adicionarTransicao("Tf");
        r.adicionarTransicao("Tg");

        r.adicionarConexao("Tb","L1");
        r.adicionarConexao("L1","Ta", 2);
        r.adicionarConexao("Ta","L4");
        r.adicionarConexao("L4","Td");
        r.adicionarConexao("L7","Td", 1, false, true);
        r.adicionarConexao("Td", "L11");
        r.adicionarConexao("L11", "Tg");
        r.adicionarConexao("Tg", "L12");
        r.adicionarConexao("Tg", "L13");
        r.adicionarConexao("L13", "Tf");
        r.adicionarConexao("Tf", "L6");
        r.adicionarConexao("Tf", "L2");
        r.adicionarConexao("L3", "Tc");
        r.adicionarConexao("Tc", "L6");
        r.adicionarConexao("L6", "Te", 2, true,false);
        r.adicionarConexao("L2", "Tb");
        r.adicionarConexao("Tb", "L5");
        r.adicionarConexao("L5", "Te");
        r.adicionarConexao("Te", "L10");
        r.adicionarConexao("Te", "L9");
        r.adicionarConexao("Te", "L8");
        r.adicionarConexao("L10", "Tf");
        r.adicionarConexao("L8", "Td");
        r.adicionarConexao("L8", "Tg");
        r.adicionarConexao("L9", "Tg");

        r.executarCiclos(true);
        */

        Scheduler s = new Scheduler();
        //System.out.println(s.exponencial(1));
        grupoEntidades filaChegada1 = new grupoEntidades("Fila de Chegada");
        grupoEntidades filaChegada2 = new grupoEntidades("Fila de Chegada");
        Chegada chegada = new Chegada(s.normal(8,3), 180, filaChegada1, filaChegada2);
        s.adicionarEvent(chegada);

        grupoEntidades balcao = new grupoEntidades("Balcao", 6);
        grupoEntidades mesas2Lugares = new grupoEntidades("Mesas2L", 4);
        grupoEntidades mesas4Lugares = new grupoEntidades("Mesas4L", 6);

        s.addGroup(balcao);
        s.addGroup(mesas2Lugares);
        s.addGroup(mesas4Lugares);

        grupoEntidades filaBalcao = new grupoEntidades("Fila Balcao");
        grupoEntidades filaMesas2Lugares = new grupoEntidades("Fila Mesas de 2 Lugares");
        grupoEntidades filaMesas4Lugares = new grupoEntidades("Fila Mesas de 4 Lugares");

        s.addGroup(filaBalcao);
        s.addGroup(filaMesas2Lugares);
        s.addGroup(filaMesas4Lugares);

        s.executar();
        System.out.println(filaChegada1.getSize());

    }
}

