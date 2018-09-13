package br.edu.ifsp.sdm.denis.bingo;

public class BingoException extends RuntimeException {

    private static final long serialVersionUID = -3456300039115364189L;

    public BingoException(String mensagem) {
        super(mensagem);
    }
}
