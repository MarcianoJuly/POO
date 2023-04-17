package br.ufla.gac106.s2022_2.tropadoPaiBola.acesso_dados;

import java.io.*;
import java.util.ArrayList;

import br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes.*;

public class ManipulaAtletas {
    private Atleta objeto;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private boolean verificador;

public boolean procura(String x){
    verificador = false;

        try{
            ois = new ObjectInputStream(new FileInputStream("Atletas.bin"));
            do{
                objeto = (Atleta)ois.readObject();

                if((objeto.getNome()).equals(x)){
                    verificador = true;
                    return verificador; 
                }
              
            }while(objeto != null);

           ois.close();
        }
        catch( ClassNotFoundException e) {

        }
        catch(IOException e){

        }
        return verificador;
}    

public void remove(String x){
    
        try{

        oos = new ObjectOutputStream(new FileOutputStream("auxiliarAtletas.bin"));
        ois = new ObjectInputStream(new FileInputStream("Atletas.bin"));

        do{               
                try {
                    objeto = (Atleta)ois.readObject();

                    if((objeto.getNome()).equals(x) == false){
                        oos.writeObject(objeto);
                    }
                    
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch(EOFException fim) {
                    ois.close(); // finaliza o stream
                    break; // sai do loop
                }

                
                   
        }while(objeto != null);

        ois.close();
        oos.close();

        passaLimpo();

      
        } catch (IOException e) {
                //System.out.println("não escreveu");
                e.printStackTrace();
        }    

       
}

//Objetivos de depuração
public ArrayList<Atleta> mostraTodos(){
    //listaTudo();
    ArrayList<Atleta> atletas = new ArrayList<Atleta>();

        try {
            ois = new ObjectInputStream(new FileInputStream("Atletas.bin"));
            
                do{
                    
                    objeto = (Atleta)ois.readObject();
                    if(objeto != null)
                        atletas.add(objeto); 
                    

                                                 
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
        return atletas;
}

public Atleta adiciona(Atleta x){
    
    Atleta retorno = null;
        //tratamento caso o arquivo ainda não tenha sido criado
        try {
            ois = new ObjectInputStream(new FileInputStream("Atletas.bin"));
            ois.close();
        }catch(FileNotFoundException e){
            try{
                oos = new ObjectOutputStream(new FileOutputStream("Atletas.bin"));
                oos.close();
            }catch(IOException f){

            }

        }catch(IOException c){

        }


            //Função de verdade
            try{

            oos = new ObjectOutputStream(new FileOutputStream("auxiliarAtletas.bin"));
            ois = new ObjectInputStream(new FileInputStream("Atletas.bin"));

            do{               
                    try {
                        objeto = (Atleta)ois.readObject();
                        if(objeto != null)
                            oos.writeObject(objeto);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch(EOFException fim) {
                        ois.close(); // finaliza o stream
                        break; // sai do loop
                    }

                    
                       
            }while(objeto != null);

            oos.writeObject(x);
            ois.close();
            oos.close();

            passaLimpo();

          
    } catch (IOException e) {
            //System.out.println("não escreveu");
            e.printStackTrace();
    }    
    
    return retorno;
}

public void listaTudo(){
    try{
        ois = new ObjectInputStream(new FileInputStream("Atletas.bin"));
        do{
            objeto = (Atleta)ois.readObject();

            System.out.println("--------------");
            System.out.println(objeto.getNome());
            System.out.println("--------------");
          
        }while(objeto != null);

       ois.close();
    }
    catch( ClassNotFoundException e) {

    }
    catch(IOException e){

    }

}   

public void passaLimpo(){
    //Passa a limpo
    try{
        ois = new ObjectInputStream(new FileInputStream("auxiliarAtletas.bin"));
        oos = new ObjectOutputStream(new FileOutputStream("Atletas.bin"));

        do{
            try {
                objeto = (Atleta)ois.readObject();
                if(objeto != null)
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

public void updateArquivo(ArrayList<Atleta> lista){
    //Passa a limpo
    try{       
        oos = new ObjectOutputStream(new FileOutputStream("Atletas.bin"));
        int i=0;

        do{
            try{
                objeto = lista.get(i);
                if(objeto != null){
                    oos.writeObject(objeto);
                }
            }catch(IndexOutOfBoundsException f){

            }
                

        i++;    
        }while(i < lista.size());

        oos.close();
    }catch(IOException e){

    }    

}

public Atleta retorna(String nome){
    verificador = false;
    Atleta retorno = null;

    try{
        ois = new ObjectInputStream(new FileInputStream("Atletas.bin"));

        do{
            objeto = (Atleta)ois.readObject();
            if((objeto.getNome()).equals(nome)){
                verificador = true;
                retorno = objeto;
            }

        }while(objeto != null && verificador == false);

        
        //Transforma a base de dados novamente em "Atleta.bin"
        ois.close();

    }
    catch(IOException e){

    }
    catch(ClassNotFoundException e){

    }
    
    
    return retorno;

}

public void ApagaTudo(){
    try{      
        oos = new ObjectOutputStream(new FileOutputStream("Atletas.bin"));
        oos.close();
    }catch(IOException e){

    }    

}


}
   

