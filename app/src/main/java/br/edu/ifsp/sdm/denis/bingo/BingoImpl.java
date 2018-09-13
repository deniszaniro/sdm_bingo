package br.edu.ifsp.sdm.denis.bingo;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


//Nesta implementação da classe bingo, todas as pedras possíveis são armazenadas em uma lista (tipo List)
//Embaralhamos a lista (shuffle) e obtemos cada uma das pedras sequencialmente
//Deve-se observar que o método shuffle() é baseado em um objeto Random
//Principal vantagem: custo computacional pequeno durante o jogo
//Principal desvantagem: a lista de pedras não pode ser exposta aos clientes. Caso contrário, será possível saber a ordem em que as pedras serão sorteadas
public class BingoImpl implements Bingo {

    private static final long serialVersionUID = 2389370814438183409L;
    private List<Integer> pedras;
    private List<Integer> pedrasSorteadas;
    private int min;
    private int max;
    private int quantidadeTotal;
    private int quantidadePedrasSorteadas;
    private Integer ultimaPedra;
    private TipoBingoEnum tipoBingo;

    public BingoImpl(TipoBingoEnum tipoBingo) {
        this.tipoBingo = tipoBingo;
        min = tipoBingo.getMin();
        max = tipoBingo.getMax();
        quantidadeTotal = max+1-min;
        pedrasSorteadas = new ArrayList<>();
        inserirPedras();
    }

    @Override
    public Integer sortearPedra() throws BingoException {
        if(quantidadePedrasSorteadas == quantidadeTotal) {
            throw new BingoException("Não há mais pedras a serem sorteadas");
        }
        ultimaPedra = pedras.get(quantidadePedrasSorteadas++);
        pedrasSorteadas.add(ultimaPedra);
        return ultimaPedra;
    }
    //Apenas obtemos cada uma das pedras da lista (não removemos a pedra)

    @Override
    public int getQuantidadePedrasSorteadas() {
        return quantidadePedrasSorteadas;
    }

    @Override
    public boolean sorteouTodas() {
        return quantidadePedrasSorteadas == quantidadeTotal;
    }

    @Override
    public Integer getUltimaPedra() {
        return ultimaPedra;
    }

    @Override
    public List<Integer> getPedrasSorteadas() {
        return pedrasSorteadas;
    }

    @Override
    public TipoBingoEnum getTipoBingo() {
        return tipoBingo;
    }

    //métodos privados
    private void inserirPedras() {
        pedras = new ArrayList<>(quantidadeTotal); //capacidade inicial
        for(int i = min; i <= max; i++) {
            pedras.add(i);
        }
        Collections.shuffle(pedras);
    }
}
