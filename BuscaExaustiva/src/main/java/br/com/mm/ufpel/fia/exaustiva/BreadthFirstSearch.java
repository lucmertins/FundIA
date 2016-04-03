package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.Board;
import br.com.mm.ufpel.fia.exaustiva.util.BoardState;
import br.com.mm.ufpel.fia.exaustiva.util.Element;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Busca em amplitude no quebra-cabeça deslizante
 *
 * @author mertins
 */
public class BreadthFirstSearch {

    private final Board board;
    private final BoardState beginState;
    private final int collectionLimit;
    private final boolean isShuffle;

    /**
     * Construtor para realizar busca em amplitude no quebra-cabeça deslizante
     *
     * @param size tamanho do tabuleiro
     * @param shuffle quantidade de embaralhamento das peças
     * @param collectionLimit limite máximo de elementos na collection
     * @param isShuffle embaralhar qual candidato é visitável primeiro (reduz a
     * repetição de ir e vir da mesma peça)
     */
    public BreadthFirstSearch(int size, int shuffle, int collectionLimit, boolean isShuffle) {
        this.board = new Board(size);
        this.beginState = this.board.shuffle(shuffle);
        this.collectionLimit = collectionLimit;
        this.isShuffle = isShuffle;
    }

    public void run() {
        Queue<BoardState> pilha = new LinkedList<>();
        pilha.add(beginState);
        int nivel = 0;
        while (!pilha.isEmpty()) {
            nivel++;
            if (pilha.size() > this.collectionLimit) {
                System.out.printf("Falha! Collection com muitos elementos[%s]\n ", pilha.size());
                throw new RuntimeException("Collection com muitos elementos");
            }
            BoardState tempState = pilha.poll();
            this.board.print(tempState);
            if (!this.board.isTheSolution(tempState)) {
                Element[] findCandidates = this.board.findCandidates(tempState, isShuffle);
                for (Element possibilidade : findCandidates) {
                    BoardState move = this.board.move(possibilidade, tempState);
                    move.setHeight(nivel);
                    pilha.add(move);
                }
            } else {
                System.out.printf("Fim com sucesso! Elementos na Collection[%d]\n", pilha.size());
                break;
            }
        }
    }
}
