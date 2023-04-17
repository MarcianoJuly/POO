package br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes;

public class AtletaAtletismo extends Atleta{
    private String provaPrincipal; 
    private String categoriaAtletismo; //maratona, pista

    public AtletaAtletismo(String nomeAtleta,String modalidade, int medalhasOlimpicas, String historia, String provaPrincipal, String categoriaAtletismo){
        super(nomeAtleta,"Atletismo", modalidade, medalhasOlimpicas, historia);
        this.provaPrincipal = provaPrincipal;
        this.categoriaAtletismo = categoriaAtletismo;
    }
    public String getProvaPrincipal(){
        return provaPrincipal;
    }

    public String getCategoriaAtletismo(){
        return categoriaAtletismo;
    }

    @Override
    public String descricaoCompleta(){
        String descricao = super.descricaoCompleta();
        descricao += "\nProva principal: "+getProvaPrincipal();
        descricao += "\nCategoria Atletismo: "+getCategoriaAtletismo();
        descricao += "\nHistoria: " + getDefinicao(); 
        return descricao;
    }


}