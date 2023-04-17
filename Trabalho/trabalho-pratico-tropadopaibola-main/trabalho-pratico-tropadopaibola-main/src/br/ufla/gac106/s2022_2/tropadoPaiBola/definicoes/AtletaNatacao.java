package br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes;

public class AtletaNatacao extends Atleta{
    private String provaPricipal; 
    private String categoriaNatacao;
    private String tipoDeProva;

    public AtletaNatacao(String nomeAtleta,String modalidade, int medalhasOlimpicas, String historia, String provaPrincipal, String categoriaNatacao,String tipoDeProva){
        super(nomeAtleta,"Natação", modalidade, medalhasOlimpicas, historia);
        this.provaPricipal = provaPrincipal;
        this.categoriaNatacao = categoriaNatacao;
        this.tipoDeProva = tipoDeProva;
    }

    public String getProvaPrincipal(){
        return provaPricipal;
    } 

    public String getCategoriaNatacao(){
        return categoriaNatacao;
    }  

    public String getTipoDeProva(){
        return tipoDeProva;
    }

     @Override
    public String descricaoCompleta(){
        String descricao = super.descricaoCompleta();
        descricao += "\nProva principal: "+getProvaPrincipal();
        descricao += "\nCategoria Natacao: "+getCategoriaNatacao();
        descricao += "\nTipo de prova: "+getTipoDeProva();
        descricao += "\nHistoria: " + getDefinicao(); 
        return descricao;
    }
}
