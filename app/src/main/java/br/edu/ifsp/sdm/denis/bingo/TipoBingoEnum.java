package br.edu.ifsp.sdm.denis.bingo;

public enum TipoBingoEnum {

    BINGO_75(1, 75);
    //outros tipos poderiam ser declarados aqui (bingo com 80 bolas, 90 bolas, e assim por diante)

    private int min;
    private int max;

    TipoBingoEnum(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
