package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.Board;
import br.com.mm.ufpel.fia.exaustiva.util.BoardState;
import br.com.mm.ufpel.fia.exaustiva.util.Elemento;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Busca em profundidade
 *
 * @author mertins
 */
public class BreadthFirstSearch {

    private final Board board;
    private final BoardState beginState;
    private final int collectionLimit;

    public BreadthFirstSearch(int size, int shuffle, int collectionLimit) {
        this.board = new Board(size);
        this.beginState = this.board.shuffle(shuffle);
        this.collectionLimit = collectionLimit;
//        this.board.print(this.beginState);
    }

    public void run() {
        Queue<BoardState> pilha = new LinkedList<>();
        pilha.add(beginState);
        int nivel = 0;
        while (!pilha.isEmpty()) {
            nivel++;
            if (pilha.size() > this.collectionLimit) {
                System.out.printf("Collection com muitos elementos [%s]\n", pilha.size());
                throw new RuntimeException("Collection com muitos elementos");
            }
            BoardState tempState = pilha.poll();
            this.board.print(tempState);
            if (!this.board.isTheSolution(tempState)) {
                Elemento[] findCandidates = this.board.findCandidates(tempState);
                for (Elemento possibilidade : findCandidates) {
                    BoardState move = this.board.move(possibilidade, tempState);
                    move.setHeight(nivel);
                    pilha.add(move);
                }
            } else {
                System.out.printf("Fim   Elementos na Collection [%d]\n", pilha.size());
                break;
            }
        }
    }
}
