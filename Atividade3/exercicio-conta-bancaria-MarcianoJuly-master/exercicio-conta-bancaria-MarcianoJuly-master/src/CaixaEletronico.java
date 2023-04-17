import java.util.Scanner;

public class CaixaEletronico {
    private ContaBancaria registrado1;
    private ContaBancaria registrado2;
    private Scanner cin;
    private Cliente utilizador;

    public CaixaEletronico(){
        cin = new Scanner(System.in);
    }

    public void Menu(){
        boolean ativo = true;
        int numeroPesquisa;
        do{
            System.out.println( "1. Criar Conta\n"+
                                "2. Consultar Saldo\n"+
                                "3. Depositar\n"+
                                "4. Sacar\n"+
                                "5. Transferir\n"+
                                "6. Sair");
            switch (Integer.parseInt(cin.nextLine())){
                    case 1:
                        caixa();
                        break;

                    case 2:
                        numeroPesquisa = perguntaConta();
                        if((registrado1!=null && registrado2!=null)){
                            consulta(numeroPesquisa);
                        }else
                            System.out.println("Cliente não registrado ou não encontrado");
                        break;

                    case 3:  
                        numeroPesquisa = perguntaConta();
                        if((registrado1!=null && registrado2!=null)){
                            depositoProcura(numeroPesquisa);
                        }else
                            System.out.println("Cliente não registrado ou não encontrado");
                        break;

                    case 4:
                        numeroPesquisa = perguntaConta();
                        if((registrado1!=null && registrado2!=null)){
                            saldoProcura(numeroPesquisa);
                        }else
                            System.out.println("Cliente não registrado ou não encontrado");
                        break;

                    case 5:
                        numeroPesquisa = perguntaConta();
                        if((registrado1!=null && registrado2!=null)){
                            int escolhaa = acharConta(numeroPesquisa);
                            System.out.println("Quanto vai ser o PIX?");
                            if(escolhaa==1){
                                registrado1.tranferencia(registrado2,Double.parseDouble(cin.nextLine()));
                            }else{
                                registrado2.tranferencia(registrado1, Double.parseDouble(cin.nextLine()));
                            }
                            System.out.println("PIX feito");
                        }else
                            System.out.println("Cliente não registrado ou não encontrado");
                        break;

                    case 6:
                        ativo = false;
                        break;

                    default:
                        System.out.println("Escolha não valida");
                    }
        }while(ativo);
    }

    public int perguntaConta(){
        System.out.println("Qual e o numero de conta que você deseja? ");
        int conta = Integer.parseInt(cin.nextLine());
        return conta;
    }

    public int acharConta(int num){
        if(registrado1.getNumero()==num)
            return 1;
        else if(registrado2.getNumero()==num)
            return 2;
        else 
            return -1;
    } 

    public void consulta(int num){
        int escolha = acharConta(num);
        if(escolha==1)
            System.out.println("O usuario: "+ registrado1.consultarNomedePessoa() +"\nTem saldo: "+ registrado1.consultarSaldo());
        else
            System.out.println("O usuario: " +registrado2.consultarNomedePessoa() +"\nTem saldo: "+ registrado2.consultarSaldo());
    }

    public void depositoProcura(int num){
        int escolha = acharConta(num);
        System.out.println("Quanto deseja depositar?");
        if(escolha==1)
            registrado1.deposito(Double.parseDouble(cin.nextLine()));
        else
            registrado2.deposito(Double.parseDouble(cin.nextLine()));
        System.out.println("Deposito concluido");
    }

    public void saldoProcura(int num){
        int escolha = acharConta(num);
        System.out.println("Deseja sacar quanto?");
        if(escolha==1){
            if(registrado1.saque(Double.parseDouble(cin.nextLine())))
                System.out.println("Saque concluido");
            else
                System.out.println("Saque não foi possivel");
        }else{
            if(registrado2.saque(Double.parseDouble(cin.nextLine())))
                System.out.println("Saque concluido");
            else
                System.out.println("Saque não foi possivel");
        }
    }

    public Cliente registrarCliente(){
        System.out.println("Registre o nome: ");
        String pessoa = cin.nextLine();
        System.out.println("E o CPF: ");
        String cpf = cin.nextLine();
        utilizador = new Cliente(cpf, pessoa);
        return utilizador;
    }

    public void caixa(){
        System.out.println("Caso deseje criar uma conta sem depositar algo inicialmente digite (1) \nCaso deseje criar com um saldo digite (2)");
            switch (Integer.parseInt(cin.nextLine())){
                    case 1:
                        registrado1 = new ContaBancaria(registrarCliente());
                        break;
                    case 2:
                        System.out.println("Digite o quanto que deseja depositar: ");
                        double deposito = Double.parseDouble(cin.nextLine());
                        registrado1 = new ContaBancaria(registrarCliente(), deposito);
                        break;
                    }
                    System.out.println("Numero da conta: " +registrado1.getNumero());

        System.out.println("Caso deseje criar uma conta sem depositar algo inicialmente digite (1) \nCaso deseje criar com um saldo digite (2)");
            switch (Integer.parseInt(cin.nextLine())){
                    case 1:
                        registrado2 = new ContaBancaria(registrarCliente());
                        break;
                    case 2:
                        System.out.println("Digite o quanto que deseja depositar: ");
                        double deposito = Double.parseDouble(cin.nextLine());
                        registrado2 = new ContaBancaria(registrarCliente(), deposito);
                        break;
                    }
                    System.out.println("Numero da conta: " +registrado2.getNumero());
            }
}