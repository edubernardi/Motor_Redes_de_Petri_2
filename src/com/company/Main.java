package com.company;

public class Main {

    public static void main(String[] args) {
        Scheduler s = new Scheduler();

        Rede redeGarcons = new Rede();

        redeGarcons.adicionarLugar("Garcons disponiveis");
        redeGarcons.adicionarLugar("Higienizando mesa");
        redeGarcons.adicionarLugar("Atendendo caixa");
        redeGarcons.adicionarLugar("Transportando refeicao");
        redeGarcons.adicionarLugar("Refeicoes aguardando transporte");
        redeGarcons.adicionarLugar("Caixa precisa ir ao banheiro");
        redeGarcons.adicionarLugar("Mesas aguardando higienizacao");

        redeGarcons.adicionarTransicao("Higienizar mesa");
        redeGarcons.adicionarTransicao("Fim higienizacao");
        redeGarcons.adicionarTransicao("Transportar refeicao");
        redeGarcons.adicionarTransicao("Fim transporte");
        redeGarcons.adicionarTransicao("Substituir caixa");
        redeGarcons.adicionarTransicao("Caixa voltou");

        redeGarcons.adicionarConexao("Garcons disponiveis", "Higienizar mesa");
        redeGarcons.adicionarConexao("Mesas aguardando higienizacao", "Higienizar mesa");
        redeGarcons.adicionarConexao("Garcons disponiveis", "Transportar refeicao");
        redeGarcons.adicionarConexao("Garcons disponiveis", "Substituir caixa");

        redeGarcons.adicionarConexao("Caixa precisa ir ao banheiro", "Substituir caixa");
        redeGarcons.adicionarConexao("Refeicoes aguardando transporte", "Transportar refeicao");

        redeGarcons.adicionarConexao("Higienizar mesa", "Higienizando mesa");
        redeGarcons.adicionarConexao("Higienizando mesa", "Fim higienizacao");
        redeGarcons.adicionarConexao("Fim higienizacao", "Garcons disponiveis");

        redeGarcons.adicionarConexao("Transportar refeicao", "Transportando refeicao");
        redeGarcons.adicionarConexao("Transportando refeicao", "Fim transporte");
        redeGarcons.adicionarConexao("Fim transporte", "Garcons disponiveis");

        redeGarcons.adicionarConexao("Substituir caixa", "Atendendo caixa");
        redeGarcons.adicionarConexao("Atendendo caixa", "Caixa voltou");
        redeGarcons.adicionarConexao("Caixa voltou", "Garcons disponiveis");

        redeGarcons.setTokens("Garcons disponiveis", 2);

        Garcon garcon = new Garcon(redeGarcons);
        s.addEntity(garcon);

        EntitySet filaChegada1 = new EntitySet("Fila de Chegada");
        EntitySet filaChegada2 = new EntitySet("Fila de Chegada");
        Chegada chegada = new Chegada(s.exponencial(3), 180, filaChegada1, filaChegada2);
        s.adicionarEvent(chegada);

        EntitySet balcao = new EntitySet("Balcao", 6);
        EntitySet mesas2Lugares = new EntitySet("Mesas2L", 4);
        EntitySet mesas4Lugares = new EntitySet("Mesas4L", 6);

        s.addGroup(balcao);
        s.addGroup(mesas2Lugares);
        s.addGroup(mesas4Lugares);

        EntitySet filaBalcao = new EntitySet("Fila Balcao");
        EntitySet filaMesas2Lugares = new EntitySet("Fila Mesas de 2 Lugares");
        EntitySet filaMesas4Lugares = new EntitySet("Fila Mesas de 4 Lugares");

        s.addGroup(filaBalcao);
        s.addGroup(filaMesas2Lugares);
        s.addGroup(filaMesas4Lugares);

        s.executar();
        System.out.println(s.getEntityTotal());
    }
}

