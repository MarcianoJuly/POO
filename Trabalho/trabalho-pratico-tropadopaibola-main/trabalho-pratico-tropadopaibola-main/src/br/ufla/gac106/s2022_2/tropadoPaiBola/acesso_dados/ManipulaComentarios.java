package br.ufla.gac106.s2022_2.tropadoPaiBola.acesso_dados;

import br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes.*;
import java.util.*;
import java.io.*;


public class ManipulaComentarios {
    private Comentario objeto;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;


//reescreve o arquivo auxiliar no arquivo principal, usada para operações de adicionar  ou remover
public void passaLimpo(){
    //Passa a limpo
    try{
        ois = new ObjectInputStream(new FileInputStream("auxiliar.bin"));
        oos = new ObjectOutputStream(new FileOutputStream("Comentarios.bin"));

        do{
            try {
                objeto = (Comentario)ois.readObject();
                oos.writeObject(objeto);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            

        }while(objeto != null);
        
        ois.close();
        oos.close();
    }catch(IOException e){

    }    

}

//adiciona um usuario no arquivo principal
public Comentario adicionaComentario(Comentario x){
    
    Comentario retorno = null;
    try{    
        
            ois = new ObjectInputStream(new FileInputStream("Comentarios.bin"));
            oos = new ObjectOutputStream(new FileOutputStream("auxiliar.bin"));
            

            do{               
                try {
                    objeto = (Comentario)ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch(EOFException fim) {
                    ois.close(); // finaliza o stream
                    break; // sai do loop
                }

                    oos.writeObject(objeto);
                       
            }while(objeto != null);

            oos.writeObject(x);
            ois.close();
            oos.close();

            passaLimpo();

            //System.out.println("Escreveu");
            retorno = x;
            
            
            

    } catch (IOException e) {
            //System.out.println("não escreveu");
            e.printStackTrace();
    }    
    
    return retorno;
}

public ArrayList<Comentario> mostraTodos(){
    //listaTudo();
    ArrayList<Comentario> Comentarios = new ArrayList<Comentario>();

        try {
            ois = new ObjectInputStream(new FileInputStream("Comentarios.bin"));
            
                do{
                    
                    objeto = (Comentario)ois.readObject();
                    if(objeto != null)
                        Comentarios.add(objeto); 
                    

                                                 
                }while(objeto != null);

            ois.close();					
                     
        }
        catch (IOException e) {       
          //System.out.println(e.getMessage());
	    }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        //listaTudo();
        return Comentarios;
}

//apaga todos os uusuarios
public void apagaTudo(){
    try {
        oos = new ObjectOutputStream(new FileOutputStream("Comentarios.bin"));
        oos.close();
    } catch (Exception e) {
        
    }
}


}
