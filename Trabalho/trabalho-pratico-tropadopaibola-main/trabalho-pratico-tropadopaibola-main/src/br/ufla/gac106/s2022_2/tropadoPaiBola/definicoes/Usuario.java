package br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes;
import java.io.Serializable;

/*Classe que define um padrão de usuário */

public abstract class Usuario implements Serializable{
    private static final long serialVersionUID = -5532792092642818016l;
    private String nome;
    private int idade;
    private String chave;

    public Usuario(String nome, int idade, String chave){
        this.chave = chave;
        this.idade = idade;
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public int getIdade(){
        return idade;
    }

    public boolean confereChave(String x){
        if(chave.equals(x)){
            return true;
        }
        else{
            return false;
        }    
        
    }

    

    public boolean podeAdicionar(){
        return false;
    }

    public boolean ehModerador(){
        return false;
    }

    public boolean ehAdministrador(){
        return false;
    }

    public boolean ehComum(){
        return false;
    }

    public boolean podeExcluir(){
        return false;
    }
}
