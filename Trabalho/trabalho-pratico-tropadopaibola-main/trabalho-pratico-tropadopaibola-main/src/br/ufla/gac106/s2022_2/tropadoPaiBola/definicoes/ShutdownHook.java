package br.ufla.gac106.s2022_2.tropadoPaiBola.definicoes;
 
import java.util.*;

import br.ufla.gac106.s2022_2.tropadoPaiBola.acesso_dados.Persistencia;

public class ShutdownHook extends Thread {
    private final ArrayList<Atleta> dataList;
    private final Persistencia armazem;

    public ShutdownHook( ArrayList<Atleta> listaAtletas) {
        this.dataList = listaAtletas;
        this.armazem = Persistencia.getPersistencia();
    }
    
    @Override
    public void run() {
        armazem.updateAtletas(dataList);
    }
}