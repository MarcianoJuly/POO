public class ContaBancaria{
    private static int numeroRegistrados = 100;
    private double saldo;
    private double limite;
    private int numeroConta;
    private Cliente pessoa;

    public ContaBancaria(Cliente registro){
        this(registro,0);
    }

    public ContaBancaria(Cliente registro, double saldoInicial){
        saldo = saldoInicial;
        limite = 2000; //não definido ainda tem que passar paramentro?
        pessoa = registro;
        numeroConta = ++numeroRegistrados;
    }

    public double consultarSaldo(){
        return saldo;
    }

    public int getNumero(){
        return numeroConta;
    }

    public String consultarNomedePessoa(){
        return pessoa.consultarNome();
    }


    public void deposito(double valor){
        saldo += valor;
    }

    public boolean saque(double valor){ 
        if((saldo>=valor) || (limite>=Math.abs(saldo-valor))){
            saldo -= valor;
            return true;
        }else
            return false;
    }

    public boolean tranferencia(ContaBancaria recebendo, double quantidade){
        if(this.saque(quantidade)){
            recebendo.deposito(quantidade);
            return true;
        }else{
            return false;
        }
    }
}