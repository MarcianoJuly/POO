public class Carro{
    //Atributos
    private int velocidade;
    private String name;
    //Construtor
    public Carro(String nome){
        name = nome;
        velocidade = 0;
    }

    //Metodos
    public String getNome(){return name;}
    public int getVelocidade(){return velocidade;}

    public void acelerarCarro(){
        velocidade +=10;
    }

    public void reduzirVel(){
        velocidade -=10;
    }

}