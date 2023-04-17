package br.ufla.gac106.s2022_2.tropadoPaiBola.acesso_dados;
import br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes.*;


import java.util.ArrayList; 

/*  SOBRE ESSA CLASSE: "Persistencia"
 * -> É a classe principal que faz a comunicação e organização da manipulação dos arquivos
 * |  que guardam Atletas, e dos arquivos que guardam Usuarios.
 * 
 */

 //OBS: ESSA CLASSE USA O PADRÃO DE PROJETO- Singleton
public class Persistencia {
    public static Persistencia SingletonPersistencia;  
    private ManipulaAtletas atletas;
    private ManipulaUsuarios usuarios;
    private ManipulaComentarios comentarios;

   private Persistencia(){
        atletas = new ManipulaAtletas();
        usuarios = new ManipulaUsuarios();
   }
     
   public static Persistencia getPersistencia(){
     if(SingletonPersistencia == null){
          SingletonPersistencia = new Persistencia();
      }
      return SingletonPersistencia;  
   }
   //apaga todos os usuarios do programa CUIDADO AO UTILIZAR
   public void apagarUsers(){
     usuarios.apagaTudo();
   }

   //responsavel pelo login do usuario, a partir do nome e da chave do mesmo
     public Usuario login(String nome, String chave){
        if(usuarios.procura(nome,chave) == true){
            return usuarios.logar(nome);          
        }else{
            return null;
        }
    
     }

     public void ApagaTudoAtletas(){
          atletas.ApagaTudo();
     }

     public void ApagaTudoUsuarios(){
          usuarios.apagaTudo();
     }

//TEM QUE RECEBER UM ATLETA PRONTO
//adiciona um atleta nos arquivos de atleta
     public boolean adicionaAtleta(Atleta x){
          atletas.adiciona(x);
          return true;
     }

     public boolean adicionaUser(Usuario user){
          usuarios.adicionaU(user);
          return true;
     }
/* 
   -- retorna um atleta especifico para ser trabalhado durante o programa principal
   
   public Atleta retornaAtletaEspecifico(String nome){
     return atletas.retorna(nome);
   }
*/
   //a partir do nome do atleta, chama o método responsavel por remover um atleta a partir do nome
   public void removeAtleta(String nome){
     atletas.remove(nome);
   }
   
   //retorna um objeto ArrayList que será utilizado para visualizar os usuarios armazenados
   public ArrayList<Atleta> LA(){        
        return atletas.mostraTodos();
   }

   public void adicionaComentario(Comentario x){
     comentarios.adicionaComentario(x);
   }

   public ArrayList<Atleta> retornaComentarios(){        
     return atletas.mostraTodos();
   }

   public void updateAtletas(ArrayList<Atleta> ListaAtletas){
     atletas.updateArquivo(ListaAtletas);
   };


}
