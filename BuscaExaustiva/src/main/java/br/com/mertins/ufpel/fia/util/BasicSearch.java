package br.com.mertins.ufpel.fia.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author mertins
 */
public abstract class BasicSearch {

    protected final Observator observator;
    protected final Board board;
    protected final BoardState beginState;
    protected final boolean isShuffle;

    /**
     * Construtor basico para busca no quebra-cabeça deslizante
     *
     * @param observator classe responsável em receber as informações que servirão ao relatório
     * @param isShuffle embaralhar qual candidato é visitável primeiro (reduz a repetição de ir e vir da mesma peça)
     */
    public BasicSearch(Observator observator, boolean isShuffle) {
        this.observator = observator;
        this.board = new Board(observator.getSize());
        this.beginState = this.board.shuffle(observator.getShuffle());
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
    
    public String time(Duration duration){
        DateTimeFormatter  fmt = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        String format = fmt.format(duration.addTo(LocalDateTime.of( 0 , 1 ,1 , 0 , 0 )));
        return format;
    }

}
