package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.Board;
import br.com.mm.ufpel.fia.exaustiva.util.BoardState;
import br.com.mm.ufpel.fia.exaustiva.util.Elemento;
import java.util.Stack;

/**
 * Busca em profundidade no quebra-cabeça deslizante
 *
 * @author mertins
 */
public class DepthFirstSearch {

    private final Board board;
    private final BoardState beginState;
    private final int collectionLimit;
    private final boolean isShuffle;

    /**
     * Construtor para realizar busca em profundidade no quebra-cabeça
     * deslizante
     *
     * @param size tamanho do tabuleiro
     * @param shuffle quantidade de embaralhamento das peças
     * @param collectionLimit limite máximo de elementos na collection
     * @param isShuffle embaralhar qual candidato é visitável primeiro (reduz a
     * repetição de ir e vir da mesma peça)
     *
     */
    public DepthFirstSearch(int size, int shuffle, int collectionLimit, boolean isShuffle) {
        this.board = new Board(size);
        this.beginState = this.board.shuffle(shuffle);
        this.collectionLimit = collectionLimit;
        this.isShuffle = isShuffle; //
        this.board.print(this.beginState);
    }

    public void run() {
        Stack<BoardState> pilha = new Stack<>();
        pilha.add(beginState);
        int nivel = 0;
        while (!pilha.isEmpty()) {
            nivel++;
            if (pilha.size() > this.collectionLimit) {
                System.out.printf("Collection com muitos elementos[ % s]\n ", pilha.size());
                throw new RuntimeException("Collection com muitos elementos");
            }
            BoardState tempState = pilha.pop();
            this.board.print(tempState);
            if (!this.board.isTheSolution(tempState)) {
                Elemento[] findCandidates
                        = this.board.findCandidates(tempState, isShuffle);
                for (Elemento possibilidade : findCandidates) {
                    BoardState move
                            = this.board.move(possibilidade, tempState);
                    move.setHeight(nivel);
                    pilha.add(move);
                }
            } else {
                System.out.printf("Fim Elementos na Collection[ % d]\n", pilha.size());
                break;
            }
        }
    }
}
