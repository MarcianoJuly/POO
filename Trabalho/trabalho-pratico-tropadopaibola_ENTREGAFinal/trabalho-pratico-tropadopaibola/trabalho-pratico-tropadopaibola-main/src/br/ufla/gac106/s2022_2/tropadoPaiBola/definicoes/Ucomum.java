package br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes;


/*Classe mais básica que consegue apenas visualizar e avaliar atletas */

public class Ucomum extends Usuario {
    
    public Ucomum(String nome, int idade, String chave){
        super(nome,idade,chave);
    }

    @Override
    public boolean ehComum(){
        return true;
    }
}
