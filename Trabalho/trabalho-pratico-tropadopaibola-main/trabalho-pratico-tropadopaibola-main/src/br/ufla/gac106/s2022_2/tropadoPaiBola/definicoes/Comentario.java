package br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Comentario {
    private String nomeUsuario;
    private String atletaComentado;
    private String conteudo;
    private int curtidas;
    private String horasPostadas;
    private Comentario comentReferido = null;
    private List<Comentario> comentariosDoComentario;

    
    
    //DEVE IMPLEMENTAR HORARIO
    public Comentario(String nomeUsuario, String conteudo, String atletaComentado){
        this.nomeUsuario= nomeUsuario;
        this.conteudo = conteudo; 
        this.curtidas = 0;
        this.atletaComentado = atletaComentado;
        comentariosDoComentario = new ArrayList<>();
        horasPostadas = getDateTime();
    }

    public void setCurtirao(){
        curtidas +=1;
    }

    public boolean getComents(){
        if(comentariosDoComentario.size() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void setComentarioReferido(Comentario comentarioP){
        this.comentReferido = comentarioP;
    }

    public Comentario getComentarioReferido(){
        return comentReferido;
    }

    public void addComentario(Comentario x){
        comentariosDoComentario.add(x);
    }
    
    private String getNomeUsuario(){
        return nomeUsuario;
    }

    private String getConteudo(){
        return conteudo;
    }

    public int getCurtidas(){
        return curtidas;
    }

    private String getHorasPostadas(){
        return horasPostadas;
    }

    public String getDateTime() {
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    Date date = new Date();
        return dateFormat.format(date);
    }

    public String getcomentarioLayout(){
        return getNomeUsuario()+" \n                 "
             + getConteudo()+" \n                 "
             +getCurtidas()+" \n                 "+getHorasPostadas()+" \n";
    }

    public String getComentariosSobrePrincipal(){
        String comentarioSequencia = getcomentarioLayout();
        for(Comentario polemico : comentariosDoComentario){
            comentarioSequencia += "\n              " + polemico.getcomentarioLayout();
        }
        return comentarioSequencia;
    }

    public String getAtleta(){
        return atletaComentado;
    }
}
