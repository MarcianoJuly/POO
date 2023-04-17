package br.ufla.gac106.s2022_2.tropadoPaiBola;

import java.util.ArrayList;
import java.util.Scanner;
import br.ufla.gac106.javaWikiAPI.JavaWikiInternalException;
import br.ufla.gac106.javaWikiAPI.UnsuccessfulHTTPRequestException;
import br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes.*;

//ATENÇÃO: user-admin  senha-admin    ->vai ser necessario caso esteja usando pela primeira vez
public class Principal{ //admin senha admin
    private RegraDeNegocio engine;
    private Usuario user;
    private boolean logado;
    private Scanner entrada;

  public Principal(){
    entrada = new Scanner(System.in);
    engine = RegraDeNegocio.getRegraDeNegocio();
    user = null;
    logado = false;
  }

  public boolean executarGenerico(int a){
      boolean permanecente = true;
      if(logado){
        switch (a){
         case 1:
              visualizarAtletas();
              break;
         case 2:
              visualizarAtletaEspecifico();
              break;
         case 3:
              informacoesUser();
              break;
          case 4:
              criarUsuarioP();
              break;
          case 5:
              if(user.podeAdicionar()){
                adicionarAtleta();
              }else if(user.ehComum()){
                permanecente = false;
              }else{
                System.out.println("Opção invalida");
              }
              break;
         case 6:
            if(user.podeExcluir()){
              excluirAtleta();
            }else if(user.ehModerador()){
              permanecente = false;
            }else{
                System.out.println("Opção invalida");
            }
            break;
          case 7:
              permanecente = false;
              break;
          case 8:
              engine.apagarAtletas();
              engine.apagarUsuarios();
              break;
          default:
            System.out.println("Opção invalida");
            break;
        }
      }else{
        if(a==1){
          criarUsuarioP();
        }else if(a==2){
          logar();
        }else{
          permanecente = false;
        }
      }
      return permanecente;
  }

  public void menu(){
  //Esse metodo retorna um vetor de 2 posições, a primeira indica : 1-deslogado, 2- logado e adm ou moderador, 3-logado e comum.
  //A segunda posição do vetor indica a opção que o usuario digitou
    System.out.println("Seja bem vindo(a): \n ");
    boolean permanece = true;
    System.out.println("Digite a operação que deseja realizar");
    do{ 
      if(logado == false){
        System.out.println(" __________________________ \n"+
                          "| ***Opções de loging***   |\n"+
                          "|--------------------------|\n"+
                          "| 1- Criar usuario         |\n"+
                          "| 2- Logar                 |\n"+
                          "| 3- Sair                  |\n"+
                          "|__________________________|\n");
      }
      else {
        if(user.ehAdministrador()){
          System.out.println("___________________________________ \n"+
                            "| *** Menu de administrador ***    |\n"+
                            "|----------------------------------|\n"+
                            "|1- Visualizar todos atletas       |\n"+
                            "|2- Visualizar atleta em especifico|\n"+
                            "| - ver e fazer comentarios também |\n"+
                            "|3- Informações user               |\n"+
                            "|4- Criar outro usuario            |\n"+
                            "|5- Adicionar atleta               |\n"+
                            "|6- Excluir Atleta                 |\n"+
                            "|7- Sair                           |\n"+
                            "|__________________________________|");
        }
        else if(user.ehModerador()){
          System.out.println("___________________________________ \n"+
                            "| *** Menu de administrador ***    |\n"+
                            "|----------------------------------|\n"+
                            "|1- Visualizar todos atletas        |\n"+
                            "|2- Visualizar atleta em especifico|\n"+
                            "|3- Informações user               |\n"+
                            "|4- Criar outro usuario            |\n"+
                            "|5- Adicionar atleta               |\n"+
                            "|6- Sair                           |\n"+
                            "|__________________________________|");
        }
        //AVISO: Durante a segunda entrega deve adicionar as opções para moderadores
        //Durante essa entrega não haverá 
        else{
          System.out.println("___________________________________\n"+
                            "| * Menu users - comum             |\n"+
                            "|----------------------------------|\n"+
                            "|1- Visualizar todos atletas       |\n"+
                            "|2- Visualizar atleta em especifico|\n"+
                            "|3- Informações user               |\n"+
                            "|4- Criar outro usuario            |\n"+
                            "|5- sair                           |\n"+
                            "|__________________________________|");
        }
      }  
      int escolha = Integer.parseInt(entrada.nextLine());
      permanece = executarGenerico(escolha); //controleDados
    }while(permanece==true);
  }

  public void criarUsuarioP(){
      String nome, chave;
      int idade;

      System.out.println("Para criar seu usuário digite nome/identificador que será utilizado");
      nome = entrada.nextLine();

      System.out.println("Informe a sua idade");
      idade = Integer.parseInt(entrada.nextLine());

      System.out.println("Digite a senha/chave que será utilizada");
      chave = entrada.nextLine();

      int escolha = 1;
      if(user!=null){
        if(user.ehAdministrador()){
            System.out.println("Vc deseja ser um usuário: 1-Comum, 2-Moderador, 3-Administrador\n Digite o numero:");
            escolha = Integer.parseInt(entrada.nextLine());
            if(escolha==1 || escolha==2 || escolha==3)
              criadorDeConta(escolha, nome, idade, chave);
            else
              System.out.println("Escolha invalida");
        }else if(user.ehModerador()){
            System.out.println("Vc deseja ser um usuário: 1-Comum, 2-Moderador\n Digite o numero:");
            escolha = Integer.parseInt(entrada.nextLine());
            if(escolha==1 || escolha==2)
              criadorDeConta(escolha, nome, idade, chave);
            else
              System.out.println("Escolha invalida");
        }else
          criadorDeConta(escolha, nome, idade, chave); //controleDados
    }else{
      criadorDeConta(escolha, nome, idade, chave);
      }
    }

  public void logar(){
      String nome, chave;
      
        System.out.println("Digite o identificador do usuário a ser logado: ");
        nome = entrada.nextLine();

        System.out.println("Digite a chave/senha do seu usuário: ");
        chave = entrada.nextLine();

        user = engine.loga(nome,chave);


        if(user == null){
          System.out.println("Senha ou usuario incorreto");
          System.out.println("Deseja sair do loging? \n [S/N]");
          String resposta = entrada.nextLine();

          if (resposta.equals("N") || resposta.equals("n")){
            logar();
          }else{
            logado = false;
            user = null;
          }
        }
        //Verifica se logou no usuario correto
        else if((user.getNome()).equals(nome))       
          logado = true;
           
          // System.out.println("-------------");
          // System.out.println("Logado em: ");
          // System.out.println(user.getNome());
          // System.out.println("-------------");
  }
  
  private void adicionarAtleta(){
      if(logado == true && user.podeAdicionar() == true ){
        System.out.println("Digite corretamete o nome do atleta que deseja adicionar");
        String nome = entrada.nextLine();

        System.out.println("Digite a quantidade de medalhas olimpicas do atleta");
        int medalhas = Integer.parseInt(entrada.nextLine());

        System.out.println("Qual esporte ele pratica?\n"+
                          "1: Atletismo\n"+
                          "2: Natação\n"+
                          "3: Futebol\n");                         
        int esporte =  Integer.parseInt(entrada.nextLine());

        System.out.println("Digite a modalidade do atleta");
        String modalidade = entrada.nextLine();

        //trata diferentes tipos de atletas **************************************************************/     
        String prova,categoria,tipodeprova, resumo;
        switch(esporte){
          //caso seja um atleta de atletismo  
          case 1:
              
              System.out.println("Digite a prova");
              prova = entrada.nextLine();

              System.out.println("Digite a categoria");
              categoria = entrada.nextLine();

              resumo = perguntaConfirma(nome);

              engine.criaAtleta(nome, medalhas, modalidade, resumo, prova, categoria);

              break;

          //caso seja um atleta de natação
          case 2:
              
              System.out.println("Digite a prova");
              prova = entrada.nextLine();

              System.out.println("Digite a categoria");
              categoria = entrada.nextLine();

              System.out.println("Digite o tipo de prova");
              tipodeprova = entrada.nextLine();

              resumo = perguntaConfirma(nome);

              engine.criaAtleta(nome, medalhas, modalidade, resumo, prova, categoria, tipodeprova);
          
              break;

          case 3:
              System.out.println("Digite a quantidade de bolas de ouro");
              int bolasOuro = Integer.parseInt(entrada.nextLine());

              System.out.println("Digite a quantdidade de gols");
              int gols = Integer.parseInt(entrada.nextLine());

              System.out.println("Digite a posição do jogador");
              String posicao = entrada.nextLine();

              resumo = perguntaConfirma(nome);
            
              engine.criaAtleta(nome, medalhas, modalidade, resumo, bolasOuro, gols, posicao);

              break;

              default:
              break;
          }

        
        
      }
      else if(logado == false){
        System.out.println("Você não está logado");
      }
      else{
        System.out.println("Você não tem acesso ao modulo de administração");
      }
     
  }

  public void informacoesUser(){
    System.out.println("-----------------------");
    System.out.println(user.getNome() +", "+ user.getIdade() +" anos");

    if(user.ehAdministrador() == true)
      System.out.println("Usuario Administrador");

    //MUDAR NA TERCEIRA PARTE
    else
    System.out.println("Usuario convencional");  

    System.out.println("-----------------------");
  }
 
  public void visualizarAtletas(){
      ArrayList<Atleta> v = engine.listaDeAtletas();     
      System.out.println("O tamanho da lista de atletas é: " + v.size());

      for(int i=0; i< v.size(); i++) {
        System.out.println(i+1 + " ------------------------------");
        System.out.println(v.get(i).getDescricao());
        System.out.println("------------------------------");
      }
    }

  public void visualizarAtletaEspecifico(){
    System.out.println("Digite o nome do atleta que deseja visualizar");
    String nome = entrada.nextLine();
    Atleta x = engine.retornaAtletaEspecifico(nome);
    
        if(x != null){
          System.out.println(" ------------------------------");
          System.out.println(x.getDescricao());
          System.out.println("------------------------------");
          System.out.println("Caso deseje comentar em algum atleta digite o id da mensagem");
          pesquisarComentario(nome);
        }else{
          System.out.println("Esse atleta não está cadastradoad");
        }  
    }
 
  public void excluirAtleta(){
    
    System.out.println("Digite o nome do atleta");
    String nome = entrada.nextLine();
    engine.removeAtleta(nome);
  }
  
  public void criadorDeConta(int escolha, String nome, int idade,String chave){
    boolean teste = engine.criarConta(escolha, nome, idade, chave);
    if(teste == true){
      System.out.println("Conta criada com sucesso");
    }else{
      System.out.println("Impossivel criar essa conta, tente outro nickname");
    }
  }

  public String perguntaConfirma(String name){
      String teste = "";
      try {
        teste = engine.getResumoAtleta(name);
      } catch (JavaWikiInternalException | UnsuccessfulHTTPRequestException e) {
        e.printStackTrace();
      }
        System.out.println("A wiki e a que você desejava?\n");
        System.out.println(teste);
        System.out.println("\n\nA wiki e a que você desejava?");
        System.out.println("\n\nDigite S ou N");
        String resposta = entrada.nextLine();
        if(resposta.equals("S") || resposta.equals("s") || resposta.equals("sim"))
          return teste;
        else{
          System.out.println("Caso deseje continuar a procura tente especificar mais digitando o nome completo, caso desista digite não");
          resposta = entrada.nextLine();
          if(resposta.equals("N") || resposta.equals("n") || resposta.equals("nao"))
            teste = "";
          else
            perguntaConfirma(resposta);
        }
        return teste;
  }

  public void pesquisarComentario(String nome){
    System.out.println("Caso deseje curtir ou comentar sobre o atleta digite (1)");
    System.out.println("Caso deseje abrir a aba comentarios de um atleta digite (2) ");
    System.out.println("Caso não deseje nada disso digite qualquer coisa");
    int escolha = Integer.parseInt(entrada.nextLine());
    if(escolha==1)
      buscarIdAtleta(nome);
    else if(escolha==2)
      mostrarComentarios(nome);
  }
  
  public void mostrarComentarios(String nome){
      Atleta a = engine.retornaAtletaEspecifico(nome);
      if(a.getComentarios()==""){
        System.out.println("Não há comentarios");
      }else{
        System.out.println(a.getComentarios());
        desejoCritica(a);
      }
  }
    
  public void buscarIdAtleta(String nome){
      Atleta a = engine.retornaAtletaEspecifico(nome);
      System.out.println("Digite 1 Caso deseja curtir: ");
      System.out.println("Digite 2 Caso queira comentar");
      int definida = Integer.parseInt(entrada.nextLine());
      if(definida==1)
        a.addCurtida();
      else if(definida==2){
        System.out.println("Poste:   \n");
        a.adicionaComentarioBio(engine.criarComentario(user.getNome(), entrada.nextLine(), a.getNome()));
      } 
  }

  public void desejoCritica(Atleta a){
    System.out.println("Deseja comentar algum comentario? Elogiar, curtir ou xingar?");
    System.out.println("Caso deseje comentar algo digite (1): "+
                       " Caso deseje curtir um comentario digite (2)");
    int definida = Integer.parseInt(entrada.nextLine());
    if(definida==1){
      System.out.println("Qual e o ID da postagem original? ");
      int id = Integer.parseInt(entrada.nextLine());
      Comentario coment = engine.procurarComentario(a,id);
      if(coment !=null){
        System.out.println("Poste:   \n");
        String hater = entrada.nextLine();
        coment.addComentario(engine.criarComentario(user.getNome(), hater, a.getNome()));
      }else{
        System.out.println("Comentario não encontrado");
      }
    }
      else if(definida==2){
      System.out.println("Qual e o ID da postagem original? ");
      int id = Integer.parseInt(entrada.nextLine());
      Comentario coment = engine.procurarComentario(a,id);
      if(coment !=null){
        coment.setCurtirao();
      }else{
         System.out.println("Comentario não encontrado");
      }
    }

  }
  // public buscarIdMnsg(){
  //   Systen.out.println("")
  // }
}






