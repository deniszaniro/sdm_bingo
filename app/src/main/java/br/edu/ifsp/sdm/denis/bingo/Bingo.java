package br.edu.ifsp.sdm.denis.bingo;

import java.io.Serializable;
import java.util.List;

public interface Bingo extends Serializable {

    Integer sortearPedra() throws BingoException;
    int getQuantidadePedrasSorteadas();
    boolean sorteouTodas();
    Integer getUltimaPedra();
    List<Integer> getPedrasSorteadas();
    TipoBingoEnum getTipoBingo();
}
//Estendemos a interface Serializable para podermos salvar e restaurar objetos do tipo Bingo em um objeto Bundle
