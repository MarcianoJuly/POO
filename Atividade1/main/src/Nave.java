import java.util.Scanner;
import java.util.Random;
/**
 * Classe que representa uma nave do jogador no jogo
 * 
 * @author Julio Cesar Alves
 * @version 2016-04-08
 */
public class Nave
{
    private Scanner entra;
    private Random aleatorio;

    // posição da nave na tela
    private int posX;
    private int posY;
    
    // velocidade da nave ao mover (em pixels)
    private final int velocidade;
    
    // dimensões da nave
    private int largura;
    private int altura;
    
    // indica se a nave do jogador esta viva
    private boolean estaViva;

    //nome
    private String nomeJogador; //mudado
    private String desenhoShild;

    //shild
    private int shildMax;
    private int shild;

    /**
     * Controi uma nave com as caracteristicas iniciais padrao
     */
    public Nave()
    {              
        //instanciar scanner e random
        entra = new Scanner(System.in);
        aleatorio = new Random();

        // tamanho padrao da nave
        largura = 50;
        altura = 50; 
        
        // velocidade da nave
        velocidade = 15;

        //instanciar Strings e shild
        nomeJogador = new String(""); 
        desenhoShild = new String("");
        shildMax = 5;
        
    }
    
    /**
     * Reinicia as característivas da nave (quando recomeca o jogo, por exemplo)
     */
    public void inicializar()
    {                       
        // posição inicial da nave na tela
        posX = 10;
        posY = 200;
        gerarEscudo(); 
        estaViva = true;
    }
    
    /**
     * Retorna a posição X da nave na tela
     */
    public int getPosX()
    {
        return posX;
    }
    
    /**
     * Retorna a posição Y da nave na tela
     */
    public int getPosY()
    {
        return posY;
    }
    
    /**
     * Retorna a largura da nave
     */
    public int getLargura()
    {
        return largura;
    }
    
    /**
     * Retorna a altura da nave
     */
    public int getAltura()
    {
        return altura;
    }
    
    /**
     * Retorna se a nave esta viva
     */
    public boolean estaViva()
    {
        return estaViva;
    }
    
    /**
     * Altera as dimensoes da nave (para que ela fique do tamanho da figura que a representa)
     * 
     * @param largura nova largura da nave
     * @param altura nova altura da nave
     */
    public void alterarTamanho(int largura, int altura)
    {
        this.largura = largura;
        this.altura = altura;
    }
    
    /**
     * Retorna o texto a ser exibido próximo à nave
     */
    public String getTextoExibicao()
    {
        // atualmente nao retorna nada
        return nomeJogador + "( " + desenhoShild + ")";
    }
    
    /**
     * Move a nave pra direita, se ela estiver viva
     */
    public void moverDireita()
    {
        if (estaViva)
        {
            posX += velocidade;
        }
    }
    
    /**
     * Move a nave pra esquerda, se ela estiver viva
     */
    public void moverEsquerda()
    {
        if (estaViva)
        {
            posX -= velocidade;
        }
    }

    /**
     * Move a nave pra cima, se ela estiver viva
     */
    public void moverCima()
    {
        if (estaViva)
        {
            posY -= velocidade;
        }
    }

    /**
     * Move a nave pra baixo, se ela estiver viva
     */
    public void moverBaixo()
    {
        if (estaViva)
        {
            posY += velocidade;
        }
    }
        
    /**
     * Realiza um tiro se a nave estiver viva
     * 
     * @return Retorna o tiro criado, ou null se a nave não atirou
     */
    public Tiro atirar()
    {  
        if (estaViva)
        {            
            return new Tiro(posX+largura, posY + (int)(altura/2), false);
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Trata quando a nave do jogador toma um tiro, se ela estiver viva
     */
    public void tomarTiro()
    {
        if(!escudo()){
            if (estaViva){
            // morre
            estaViva = false;
            }
        }
    }

    public void alterarNome(){
        nomeJogador = entra.nextLine(); 
    }

    public boolean escudo(){
        if(shild==0)
            return false;
        else{
            shild -=1;
            desenhoShild = "";
            for(int ba=0; ba<shild; ba++)
                desenhoShild += "* ";
            return true;
        }
    }

    public void gerarEscudo(){
        shild = aleatorio.nextInt(shildMax);
        desenhoShild = "";
        for(int esc=0; esc<shild; esc++)
                desenhoShild += "* ";
    }
}
