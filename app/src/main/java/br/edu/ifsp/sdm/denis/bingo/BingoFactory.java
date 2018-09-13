package br.edu.ifsp.sdm.denis.bingo;

public class BingoFactory {

    public Bingo criarBingo(TipoBingoEnum tipoBingo) {
        return new BingoImpl(tipoBingo);
    }
}
