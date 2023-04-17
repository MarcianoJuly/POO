package br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes;

public class AtletaFutebol extends Atleta {
    private int bolasDeOuro;
    private int qtdDeGols;
    private String posicaoJogo;

    public AtletaFutebol(String nomeAtleta,String modalidade, int medalhasOlimpicas, String historia, int bolasDeOuro, int qtdGols, String posicaoJogo){
        super(nomeAtleta,"Futebol", modalidade, medalhasOlimpicas, historia);
        this.bolasDeOuro = bolasDeOuro;
        this.qtdDeGols = qtdGols;
        this.posicaoJogo = posicaoJogo;
    }  

    public int getBolasDeOuro(){
        return bolasDeOuro;
    }

    public int getQtdDeGols(){
        return qtdDeGols;
    }

    public String getPosicaoJogo(){
        return posicaoJogo;
    }

    @Override
    public String descricaoCompleta(){
        String descricao = super.descricaoCompleta();
        descricao += "\nQuantidade de bolasDeOuro: "+ getBolasDeOuro();
        descricao += "\nQuantidade de Gols: "+getQtdDeGols();
        descricao += "\n Posicao do jogador: "+getPosicaoJogo();
        descricao += "\nHistoria: " + getDefinicao(); 
        return descricao;
    }


}
