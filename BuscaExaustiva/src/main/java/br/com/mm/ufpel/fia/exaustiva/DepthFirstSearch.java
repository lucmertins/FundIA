package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.Board;
import br.com.mm.ufpel.fia.exaustiva.util.BoardState;
import br.com.mm.ufpel.fia.exaustiva.util.Elemento;
import java.util.Stack;

/**
 * Busca em profundidade
 *
 * @author mertins
 */
public class DepthFirstSearch {

    private final Board board;
    private final BoardState beginState;
    private final int collectionLimit;

    public DepthFirstSearch(int size, int shuffle, int collectionLimit) {
        this.board = new Board(size);
        this.beginState = this.board.shuffle(shuffle);
        this.collectionLimit = collectionLimit;
//        this.board.print(this.beginState);
    }

    public void run() {
        Stack<BoardState> pilha = new Stack<>();
        pilha.add(beginState);
        int nivel = 0;
        while (!pilha.isEmpty()) {
            nivel++;
            if (pilha.size() > this.collectionLimit) {
                System.out.printf("Collection com muitos elementos [%s]\n", pilha.size());
                throw new RuntimeException("Collection com muitos elementos");
            }
            BoardState tempState = pilha.pop();
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
