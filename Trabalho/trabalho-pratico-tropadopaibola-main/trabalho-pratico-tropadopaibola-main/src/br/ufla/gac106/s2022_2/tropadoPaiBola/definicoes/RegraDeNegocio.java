package br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes;

import java.util.*;
import br.ufla.gac106.javaWikiAPI.Wiki;
import br.ufla.gac106.javaWikiAPI.JavaWikiInternalException;
import br.ufla.gac106.javaWikiAPI.UnsuccessfulHTTPRequestException;
import br.ufla.gac106.javaWikiAPI.PaginaWiki;
import br.ufla.gac106.s2022_2.tropadoPaiBola.acesso_dados.Persistencia;

/*Observações da classe:
 * - Sobre trabalhar com os dados em memoria pricipal
 *    -> Os Usuarios possuem senhas vinvuladas aos mesmos, na visão do grupo não pareceu prudente
 *    carregar tudo em memoria Ram, diferente dos Atletas que estão carregados nessa classe.
 * 
 * - Sobre o uso do Singleton nessa classe
 *   -> Não fazia sentido ter mais de uma classe que carregasse os dados cadastrados em memoria principal,
 *   e essa classe ao ser encerrada inicia uma thread ShutdownHook, que salva os atletas em memoria principal
 *   para memoria secundaria. 
 */

public class RegraDeNegocio {
    public static RegraDeNegocio SingletonRegra; 
    private ArrayList<Atleta> listaAtletas;     
    private ShutdownHook shutdownHook;
    private Wiki BaseDados;
    private Persistencia armazenamento;

    private RegraDeNegocio(){
        BaseDados = new Wiki();
        armazenamento = Persistencia.getPersistencia();
        updateListaDeAtletas();
        shutdownHook = new ShutdownHook(getLista());
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    public static RegraDeNegocio getRegraDeNegocio(){
        if(SingletonRegra == null){
             SingletonRegra = new RegraDeNegocio();
         }
         return SingletonRegra;  
      }

    private  ArrayList<Atleta> getLista(){
        return listaAtletas;
    } 
/*Modificadores de Atletas -- **************************************************************************************************************** */
    public String getResumoAtleta(String nome) throws JavaWikiInternalException, UnsuccessfulHTTPRequestException{
        PaginaWiki pagina;
        pagina = BaseDados.consultarPagina(nome);
        return pagina.getResumo();
    }
    //Atletismo
    public boolean criaAtleta(String nome, int medalhas,String resumo, String modalidade,String prova, String categoria){
        listaAtletas.add(new AtletaAtletismo(nome, modalidade, medalhas, resumo, prova, categoria));
        return true;
     
    }
    //Natação
    public boolean criaAtleta(String nome, int medalhas, String modalidade,String resumo,String prova, String categoria, String tipodeprova){
        listaAtletas.add(new AtletaNatacao(nome, modalidade, medalhas, resumo, prova, categoria, tipodeprova));
        return true;
    }
    //Futebol
    public boolean criaAtleta(String nome, int medalhas,String resumo, String modalidade,int bolasOuro, int gols, String posicao){ 
        listaAtletas.add(new AtletaFutebol(nome, "Futebol",  medalhas,  resumo,  bolasOuro,  gols, posicao));
        return true;
    }

    public void apagarAtletas(){
        armazenamento.ApagaTudoAtletas();
        listaAtletas = null;
        updateListaDeAtletas();
    }

    public void apagarUsuarios(){
        armazenamento.ApagaTudoUsuarios();
    }

    public Atleta retornaAtletaEspecifico(String nome){
        Atleta x = null;  
        for(int i=0; i< listaAtletas.size(); i++) {
          if(listaAtletas.get(i).getNome().equals(nome)){ ///jogar para regraDenegocios
            x = listaAtletas.get(i);
            i = listaAtletas.size()+1;
          }
        }
        return x;
    }
    
    public void removeAtleta(String nome){
        for(int i=0; i<listaAtletas.size(); i++){
            if(listaAtletas.get(i).getNome().equals(nome)){
                Atleta x = listaAtletas.get(i);
                listaAtletas.remove(x);
                i = listaAtletas.size()+1;
            }
            
        }
    }
/*FIM -- Modificadores de Atletas -- && INICIO -- Modificadores Comentarios ****************************************************************** */

    public Comentario criarComentario(String nomeUsuario, String conteudo, String atletaComentado){
        Comentario x = new Comentario(nomeUsuario, conteudo, atletaComentado);
        return x;
    }

    public Comentario criarComentario(String nomeUsuario, String conteudo, String atletaComentado, Comentario comentado){
        Comentario x = new Comentario(nomeUsuario, conteudo, atletaComentado);
        x.setComentarioReferido(comentado);
        return x;
    }

    public Comentario procurarComentario(Atleta a, int id){
        List<Comentario> x = a.getList();
        if(id<=x.size()){
            return x.get(id-1);
        }else
            return null; 
    }


/*FIM -- Modificadores de Comentarios -- ****************************************************************************************************** */
    

    public boolean criarConta(int a, String nome, int idade, String chave){
        boolean result = false;
        Usuario user = null;
        switch (a){ 
            case 1:
                user = new Ucomum(nome,idade,chave);
                armazenamento.adicionaUser(user);
                result = true;
                break;
            case 2:
                user = new Umoderador(nome,idade,chave);
                armazenamento.adicionaUser(user);
                result = true;
                break;
            case 3:
                user = new Uadministrador(nome,idade,chave);
                armazenamento.adicionaUser(user);
                result = true;
                break;
            default:
            break;
        }    

    return result;
    }

    public ArrayList<Atleta>listaDeAtletas(){
        return armazenamento.LA();
    }

    private void updateListaDeAtletas(){
        listaAtletas = armazenamento.LA();
    }

    public Usuario loga(String nome, String chave){
        return armazenamento.login(nome,chave);
    }

}


    




