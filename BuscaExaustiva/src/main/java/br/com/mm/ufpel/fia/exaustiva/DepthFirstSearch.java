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
public class DepthFirstSearch {

    private final Board board;
    private final BoardState beginState;

    public DepthFirstSearch(int size, int shuffle) {
        this.board = new Board(size);
        beginState = this.board.shuffle(shuffle);
//        this.board.print(this.beginState);
    }

    public void run() {
        Queue<BoardState> pilha = new LinkedList<>();
        pilha.add(beginState);
        while (!pilha.isEmpty()) {
            BoardState tempState = pilha.poll();
            this.board.print(tempState);
            if (!this.board.isTheSolution(tempState)) {
                Elemento[] findCandidates = this.board.findCandidates(tempState);
                for (Elemento possibilidade:findCandidates){
                    pilha.add(this.board.move(possibilidade, tempState));
                }
            }else{
                System.out.println("Fim");
                break;
            }
        }
    }

    public static void main(String[] args) {
        DepthFirstSearch search = new DepthFirstSearch(3, 1);
        search.run();
    }
}
