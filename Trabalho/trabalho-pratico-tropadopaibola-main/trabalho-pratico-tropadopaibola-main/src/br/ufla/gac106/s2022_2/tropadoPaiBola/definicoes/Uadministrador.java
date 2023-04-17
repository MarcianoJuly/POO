package br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes;

public class Uadministrador extends Usuario{
    private static final long serialVersionUID = 5844397983979765774l;

    public Uadministrador(String nome, int idade, String chave){
        super(nome,idade,chave);
    }

    @Override
    public boolean ehAdministrador(){
        return true;
    }

    @Override
    public boolean podeAdicionar(){
        return true;
    }

    @Override
    public boolean podeExcluir(){
        return true;
    }
}
