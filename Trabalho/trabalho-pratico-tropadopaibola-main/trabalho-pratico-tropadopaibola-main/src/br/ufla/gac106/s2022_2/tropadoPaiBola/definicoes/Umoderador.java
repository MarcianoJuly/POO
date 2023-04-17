package br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes;

public class Umoderador extends Usuario {
    private static final long serialVersionUID = 1l;
    
    public Umoderador(String nome, int idade, String chave){
        super(nome,idade,chave);
    }

    @Override
    public boolean ehModerador(){
        return true;
    }

    @Override
    public boolean podeAdicionar(){
        return true;
    }
    
}
