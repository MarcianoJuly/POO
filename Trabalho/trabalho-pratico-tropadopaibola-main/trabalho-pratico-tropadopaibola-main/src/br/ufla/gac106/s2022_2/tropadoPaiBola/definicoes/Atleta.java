package br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes;
import java.util.*;
import java.io.Serializable;

public class Atleta implements Serializable{
    private static final long serialVersionUID = 1l;
    private String nome;
    private String esporte;
    private String historia;
    private String modalidade;
    private int medalhasOlimpicas;
    private int curtidas;
    private List<Comentario> comentariosSobreAtletas;


    public Atleta(String nome, String esporte, String modalidade, int medalhasOlimpicas,String historia){
        this.nome = nome;
        this.esporte = esporte;
        this.medalhasOlimpicas = medalhasOlimpicas;
        this.modalidade = modalidade;
        this.historia = historia;
        curtidas = 0;
        comentariosSobreAtletas = new ArrayList<Comentario>();
    }

    public String getNome(){
        return nome;
    }

    public String getEsporte(){
        return esporte;
    }
    
    public String getDefinicao(){
        return historia;
    }

    public int getMedalhas(){
        return medalhasOlimpicas;
    }

    public String getModalidade(){
        return modalidade;
    }

    public void adicionaComentarioBio(Comentario a){
        comentariosSobreAtletas.add(a);
    }

    public void addCurtida(){
        curtidas++;
    }

    public int getCurtidas(){
        return curtidas;
    }

    public String descricaoCompleta(){
        String tudoSobre = "";
        tudoSobre = getNome() +"\n Esporte: "+ getEsporte() +  "\n Modalidade: " + getModalidade()+"\n medalhas olimpicas: " + getDefinicao() ;
        return tudoSobre;
    }

    public String getComentarios(){
        String allComents = "";
        int idRandom = 1;
        for(Comentario x : comentariosSobreAtletas){
            allComents += idRandom + ":Em resposta a " + getNome() +" \n" +x.getcomentarioLayout() + " \n";
            idRandom++;
        }
        return allComents;
    }

    public String getDescricao(){
        return getNome() +"\nEsporte: "+ getEsporte() +  "\nModalidade: " + getModalidade() + "\nCurtidas: " + curtidas +" Comentarios sobre: " + comentariosSobreAtletas.size();
    }    

    public List<Comentario> getList(){
        return comentariosSobreAtletas;
    }

   

    public void ordenaComentariosPorCurtidas(List<Comentario> comentariosSobreAtletas) {
        Collections.sort(comentariosSobreAtletas, new Comparator<Comentario>() {
            @Override
            public int compare(Comentario c1, Comentario c2) {
                return Integer.compare(c2.getCurtidas(), c1.getCurtidas());
            }
        });   
    }
   
    
}
