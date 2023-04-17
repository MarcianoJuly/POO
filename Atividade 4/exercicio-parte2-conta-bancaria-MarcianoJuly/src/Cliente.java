public class Cliente {
    private String CPF;
    private String nome;

    public Cliente(String registro, String nomezao){
        CPF = registro;
        nome = nomezao;
    }

    public String consultarCPF(){
        return CPF;
    }

    public String consultarNome(){
        return nome;
    }


}
