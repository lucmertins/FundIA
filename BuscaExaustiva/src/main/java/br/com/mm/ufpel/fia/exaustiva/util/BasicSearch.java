package br.com.mm.ufpel.fia.exaustiva.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author mertins
 */
public abstract class BasicSearch {

    protected final Board board;
    protected final BoardState beginState;
    protected final boolean isShuffle;

    /**
     * Construtor basico para busca no quebra-cabeça deslizante
     *
     * @param size tamanho do tabuleiro
     * @param shuffle quantidade de embaralhamento das peças
     * @param isShuffle embaralhar qual candidato é visitável primeiro (reduz a
     * repetição de ir e vir da mesma peça)
     */
    public BasicSearch(int size, int shuffle, boolean isShuffle) {
        this.board = new Board(size);
        this.beginState = this.board.shuffle(shuffle);
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy hh:MM:ss.SSSS");
        System.out.printf("************* %s ************ \n", sdf.format(new Date()));
    }

    protected List<BoardState> makeSolution(BoardState estadoFinal) {
        Stack<BoardState> pilhaTemp = new Stack<>();
        while (estadoFinal.getFather() != null) {
            pilhaTemp.add(estadoFinal);
            estadoFinal = estadoFinal.getFather();
        }
        Stack<BoardState> solucao = new Stack<>();
        while (!pilhaTemp.isEmpty()) {
            solucao.add(pilhaTemp.pop());
        }
        return solucao;
    }

}
