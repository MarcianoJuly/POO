import java.util.Scanner;

import java.util.ArrayList;

public class CaixaEletronico {
    private ArrayList<ContaBancaria> registrado1;
    private Scanner cin;
    private Cliente utilizador;

    public CaixaEletronico(){
        cin = new Scanner(System.in);
        registrado1 = new ArrayList<>();
    }

    public void Menu(){
        boolean ativo = true;
        int numeroPesquisa;
        ContaBancaria registrado;
        do{
            System.out.println( "1. Criar Conta\n"+
                                "2. Consultar Saldo\n"+
                                "3. Depositar\n"+
                                "4. Sacar\n"+
                                "5. Transferir\n"+
                                "6. Listar todas as contas registradas\n"+
                                "7. Deletar conta especifica\n"+
                                "8. Sair");
            switch (Integer.parseInt(cin.nextLine())){
                    case 1:
                        caixa();
                        break;

                    case 2:
                        numeroPesquisa = perguntaConta();
                        registrado = acharConta(numeroPesquisa);
                        if(registrado!=null){
                            consulta(registrado);
                        }else
                            System.out.println("Cliente não registrado ou não encontrado");
                        break;

                    case 3:  
                        numeroPesquisa = perguntaConta();
                        registrado = acharConta(numeroPesquisa);
                        if(registrado!=null){
                            depositoProcura(registrado);
                        }else
                            System.out.println("Cliente não registrado ou não encontrado");
                        break;

                    case 4:
                        numeroPesquisa = perguntaConta();
                        registrado = acharConta(numeroPesquisa);
                        if(registrado!=null){
                            saldoProcura(registrado);
                        }else
                            System.out.println("Cliente não registrado ou não encontrado");
                        break;

                    case 5:
                        numeroPesquisa = perguntaConta();
                        registrado = acharConta(numeroPesquisa);
                        if(registrado!=null){
                            System.out.println("Para qual conta deseja tranferir?");
                            numeroPesquisa = Integer.parseInt(cin.nextLine());
                            ContaBancaria escolha = acharConta(numeroPesquisa);
                            if(escolha!=null){
                                System.out.println("Quanto vai ser o PIX?");
                                if(registrado.tranferencia(escolha,Double.parseDouble(cin.nextLine())))
                                    System.out.println("PIX feito");
                                else
                                    System.out.println("Não foi possivel realizar o PIX");
                            }else
                                System.out.println("Cliente não registrado ou não encontrado");
                        }else
                            System.out.println("Cliente não registrado ou não encontrado");
                        break;
                    
                    case 6:
                        listarContas();
                        break;
                    
                    case 7:
                        numeroPesquisa = perguntaConta();
                        registrado = acharConta(numeroPesquisa);
                        if(registrado.consultarSaldo()>0){
                            System.out.println("Essa conta possui saldo, deseja deletar mesmo assim?\n(1) Sim   (2) Não");
                            if(Integer.parseInt(cin.nextLine())==1){
                                registrado1.remove(registrado);
                                System.out.println("Conta excluida com sucesso");                            
                            }else
                                System.out.println("Conta não excluida, retire o dinheiro ou repita o metodo novamente");
                        }else if(registrado.consultarSaldo()<0){
                            System.out.println("Não e possivel excluir conta em debito");
                        }else{
                            registrado1.remove(registrado);
                            System.out.println("Conta excluida com sucesso");
                        }
                        break;

                    case 8:
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

    public ContaBancaria acharConta(int num){
        for(ContaBancaria registro : registrado1)
            if(registro.getNumero()==num)
                return registro;
        return null;
    } 

    public void consulta(ContaBancaria escolha){
        System.out.println("O usuario: "+ escolha.consultarNomedePessoa() +"\nTem saldo: "+ escolha.consultarSaldo());
    }

    public void listarContas(){
        for(ContaBancaria listar : registrado1){
            System.out.println(listar.getNumero());
            consulta(listar);
        }
    }

    public void depositoProcura(ContaBancaria escolha){
        System.out.println("Quanto deseja depositar?");
        escolha.deposito(Double.parseDouble(cin.nextLine()));
        System.out.println("Deposito concluido");
    }

    public void saldoProcura(ContaBancaria escolha){
        System.out.println("Deseja sacar quanto?");
        if(escolha.saque(Double.parseDouble(cin.nextLine())))
            System.out.println("Saque concluido");
        else    
            System.out.println("Saque não foi possivel");
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
        ContaBancaria registra;        
            switch (Integer.parseInt(cin.nextLine())){
                case 1:
                    registra = new ContaBancaria(registrarCliente());
                    System.out.println("Numero da conta: " + registra.getNumero());
                    registrado1.add(registra);
                    break;
                case 2:
                    System.out.println("Digite o quanto que deseja depositar: ");
                    double deposito = Double.parseDouble(cin.nextLine());
                    registra = new ContaBancaria(registrarCliente(), deposito);
                    System.out.println("Numero da conta: " + registra.getNumero());
                    registrado1.add(registra);
                    break;
            }
    }
}