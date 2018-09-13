package br.edu.ifsp.sdm.denis.bingo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Nesta implementação da classe bingo, todas as pedras possíveis são armazenadas em uma lista (tipo List)
//Sorteamos um número representando a posição da lista (índice) e removemos a pedra correspondente
//Principal vantagem: caso seja necessário expor a outras classes a lista de pedras, não haverá como saber a ordem em que as pedras serão sorteadas
//Principal desvantagem: a cada remoção, a lista é reorganizada para que não haja posições "vazias". Custo computacional considerável
public class Bingo2Impl implements Bingo {

    private static final long serialVersionUID = 2389370814438183409L;
    private List<Integer> pedras;
    private List<Integer> pedrasSorteadas;
    private int min;
    private int max;
    private int quantidadeTotal;
    private int quantidadePedrasSorteadas;
    private Integer ultimaPedra;
    private TipoBingoEnum tipoBingo;

    public Bingo2Impl(TipoBingoEnum tipoBingo) {
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
        Random random = new Random();
        int posicao = random.nextInt(pedras.size()); //na primeira vez, de 0 a 74, depois, de 0 a 73, e assim por diante
        ultimaPedra = pedras.remove(posicao);
        pedrasSorteadas.add(ultimaPedra);
        quantidadePedrasSorteadas++;
        return ultimaPedra;
    }

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
    }
}
