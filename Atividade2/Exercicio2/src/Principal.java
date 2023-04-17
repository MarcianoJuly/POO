import java.util.Scanner;



public class Principal {

    //atributos
    private Carro hotwhells[];
    private Scanner cin;
    private static int tamMax;
    private int tam;

    //construtor
    public Principal(){
        cin = new Scanner(System.in);
        hotwhells = new Carro[5];
        tamMax = 5;
        tam = 0;
    }

    //menu
    public void exibirMenu(){
        boolean continua = true;
        do{
        System.out.println("1) Criar o carro \n2) Acelerar o carro \n3) Reduzir a velocidade \n4) Exibir todos os dados de carros \n5) Sair");
        int escolha = Integer.parseInt(cin.nextLine());
            switch(escolha){
                case 1:
                    if(!estaCheio()){
                        registrarCarro();
                    }else{
                        System.out.println("Programa cheio");
                    }
                break;

                case 2:
                    System.out.println("Qual carro você deseja acelerar? ");
                    procurarNome(cin.nextLine(), true);
                break;

                case 3:
                    System.out.println("Qual carro freiou? ");
                    procurarNome(cin.nextLine(), false);
                break;

                case 4:
                    lerVetorNome();
                break;

                case 5:
                    System.out.println("Quardou a coleção de hotwheels");
                    continua = false;
                break;

                default:
                    System.out.println("Comando não encontrado");
                break;
            }
        }while(continua);
    }

    public boolean estaCheio(){
        return (tam==tamMax);
    }

    public void registrarCarro(){
        System.out.println("Digite um nome de carro: ");
        hotwhells[tam] = new Carro(cin.nextLine());
        tam++;
    }

    public void lerVetorNome(){
        for(int i=0; i<tam; i++){
            System.out.println("Nome: " + hotwhells[i].getNome() + "\n" + "Velocidade: " + hotwhells[i].getVelocidade() + "\n" );
        }
    }

    public void procurarNome(String palavra, boolean verdade){
        int posicao = -1;
        boolean existe = false;
        for(int i=0; i<tam ; i++){
            if(palavra.equals(hotwhells[i].getNome())){
                posicao = i;
                i = tam;
                existe = true;
            }
        }if(!existe)
            System.out.print("Não há resposta, por favor escolha a opção 4 para avaliar se o carro que deseja esta devidamente registrado.");
        else{
            if(verdade){
                System.out.println("Vrum...Vrum..." + hotwhells[posicao].getNome() + " disparou!!!");
                hotwhells[posicao].acelerarCarro();
            }else{
                System.out.println("Irlllll...Irllllll "+ hotwhells[posicao].getNome() + "desacelerou bruscamente");
                hotwhells[posicao].reduzirVel();
            }
        }
    }
}
