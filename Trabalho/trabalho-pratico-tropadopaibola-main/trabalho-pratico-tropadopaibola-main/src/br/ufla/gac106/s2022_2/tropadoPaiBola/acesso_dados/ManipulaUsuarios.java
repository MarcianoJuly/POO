package br.ufla.gac106.s2022_2.tropadoPaiBola.acesso_dados;

/*
import java.io.*;

import br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes.*;

public class ManipulaUsuarios {
    private Usuario objeto;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private boolean verificador;

//procura um determinado usuario no arquivo e confere se a chave passada corresponde   
public boolean procura(String nome, String chave){
    verificador = false;
    Usuario devido = null;

        try{
            ois = new ObjectInputStream(new FileInputStream("Usuarios.bin"));
            do{
                objeto = (Usuario)ois.readObject(); // lê

                if((objeto.getNome()).equals(nome)){
                    verificador = true;
                    devido = objeto;
                }
              
            }while(objeto != null);

           ois.close();
        }
        catch( ClassNotFoundException e) {

        }
        catch(IOException e){

        }

        
        if(verificador == true){
            if(devido.confereChave(chave)){
                //System.out.println("ManipulaUsuario- Procura| senha ta certa");
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }                     
}    

//reescreve o arquivo auxiliar no arquivo principal, usada para operações de adicionar  ou remover
public void passaLimpo(){
    //Passa a limpo
    try{
        ois = new ObjectInputStream(new FileInputStream("auxiliar.bin"));
        oos = new ObjectOutputStream(new FileOutputStream("Usuarios.bin"));

        do{
            try {
                objeto = (Usuario)ois.readObject();
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
public Usuario adicionaU(Usuario x){
    
    Usuario retorno = null;
    try{    
        
            ois = new ObjectInputStream(new FileInputStream("Usuarios.bin"));
            oos = new ObjectOutputStream(new FileOutputStream("auxiliar.bin"));
            

            do{               
                try {
                    objeto = (Usuario)ois.readObject();
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

//retorna um usuario com a sua senha alterada, para que seja manipulado em outras classes
public Usuario logar(String x){
    
  
    Usuario enviar = null;
    verificador = false;

       try{
            ois = new ObjectInputStream(new FileInputStream("Usuarios.bin"));
            do{
                objeto = (Usuario)ois.readObject();

                if((objeto.getNome()).equals(x)){
                    verificador = true;
                }
              
            }while(objeto != null && verificador == false);

           enviar = objeto;           
           ois.close();         
        }
        catch( ClassNotFoundException e) {
            e.getException();
        }
        catch(IOException e){
            e.getMessage();
        }
        
        if(enviar == null){
            //System.out.println("erro manipula");
        }
        else{
            //System.out.println(enviar.getNome());
        }    
        return enviar;
} 


//apaga todos os uusuarios
public void apagaTudo(){
    try {
        oos = new ObjectOutputStream(new FileOutputStream("Usuarios.bin"));
        oos.close();
    } catch (Exception e) {
        
    }
}

}
   
*/


import java.io.*;
import br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes.*;

public class ManipulaUsuarios {
    private Usuario objeto;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private boolean verificador;

//procura um determinado usuario no arquivo e confere se a chave passada corresponde   
public boolean procura(String nome, String chave){
    verificador = false;
    Usuario devido = null;

        try{
            ois = new ObjectInputStream(new FileInputStream("Usuarios.bin"));
            do{
                objeto = (Usuario)ois.readObject(); // lê

                if((objeto.getNome()).equals(nome)){
                    verificador = true;
                    devido = objeto;
                }
              
            }while(objeto != null);

           ois.close();
        }
        catch( ClassNotFoundException e) {

        }
        catch(IOException e){

        }

        
        if(verificador == true){
            if(devido.confereChave(chave)){
                //System.out.println("ManipulaUsuario- Procura| senha ta certa");
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }                     
}    

//reescreve o arquivo auxiliar no arquivo principal, usada para operações de adicionar  ou remover
public void passaLimpo(){
    //Passa a limpo
    try{
        ois = new ObjectInputStream(new FileInputStream("auxiliar.bin"));
        oos = new ObjectOutputStream(new FileOutputStream("Usuarios.bin"));

        do{
            try {

                objeto = (Usuario)ois.readObject();
                if(objeto != null)
                    oos.writeObject(objeto);
                System.out.println(objeto.getNome());
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
public Boolean adicionaU(Usuario x){
   
    try{    
        
            ois = new ObjectInputStream(new FileInputStream("Usuarios.bin"));
            oos = new ObjectOutputStream(new FileOutputStream("auxiliar.bin"));
            

            do{               
                try {
                    objeto = (Usuario)ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch(EOFException fim) {
                    ois.close(); // finaliza o stream
                    break; // sai do loop
                }
                    if(objeto!= null)
                     oos.writeObject(objeto);
                       
            }while(objeto != null);

            oos.writeObject(x);
            System.out.println(x.getNome() + "teste em x");
            ois.close();
            oos.close();

            passaLimpo();

            //System.out.println("Escreveu");
            return true;
            
            
            

    } catch (IOException e) {
            //System.out.println("não escreveu");
            e.printStackTrace();
            return false;
    }    
    
    
}

//retorna um usuario com a sua senha alterada, para que seja manipulado em outras classes
public Usuario logar(String x){
    
  
    Usuario enviar = null;
    verificador = false;

       try{
            ois = new ObjectInputStream(new FileInputStream("Usuarios.bin"));
            do{
                objeto = (Usuario)ois.readObject();

                if((objeto.getNome()).equals(x)){
                    verificador = true;
                }
              
            }while(objeto != null && verificador == false);

           enviar = objeto;           
           ois.close();         
        }
        catch( ClassNotFoundException e) {
            e.getException();
        }
        catch(IOException e){
            e.getMessage();
        }
        
        if(enviar == null){
            //System.out.println("erro manipula");
        }
        else{
            //System.out.println(enviar.getNome());
        }    
        return enviar;
} 


//apaga todos os uusuarios
public void apagaTudo(){
    try {
        oos = new ObjectOutputStream(new FileOutputStream("Usuarios.bin"));
        oos.close();
    } catch (Exception e) {
        // TODO: handle exception
    }
}

}