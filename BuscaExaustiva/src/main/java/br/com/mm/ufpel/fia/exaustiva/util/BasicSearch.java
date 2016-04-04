package br.com.mm.ufpel.fia.exaustiva.util;

import java.util.Date;
import java.util.List;

/**
 *
 * @author mertins
 */
public abstract class BasicSearch {

    protected final Board board;
    protected final BoardState beginState;
    protected final int collectionLimit;
    protected final boolean isShuffle;

    /**
     * Construtor basico para busca no quebra-cabeça deslizante
     *
     * @param size tamanho do tabuleiro
     * @param shuffle quantidade de embaralhamento das peças
     * @param collectionLimit limite máximo de elementos na collection
     * @param isShuffle embaralhar qual candidato é visitável primeiro (reduz a
     * repetição de ir e vir da mesma peça)
     */
    public BasicSearch(int size, int shuffle, int collectionLimit, boolean isShuffle) {
        this.board = new Board(size);
        this.beginState = this.board.shuffle(shuffle);
        this.collectionLimit = collectionLimit;
        this.isShuffle = isShuffle;
    }

    public abstract List<BoardState> run();

    public void print(List<BoardState> lista) {
        this.board.print(lista);
    }

    public void print(BoardState boardState) {
        this.board.print(boardState);
    }

    public BoardState getBeginState() {
        return beginState;
    }

    public void printTime() {
        System.out.printf("************* %s ************ \n", new Date());
    }

}
