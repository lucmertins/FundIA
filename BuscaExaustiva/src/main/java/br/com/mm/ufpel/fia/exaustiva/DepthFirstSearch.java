package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.Board;
import br.com.mm.ufpel.fia.exaustiva.util.BoardState;
import br.com.mm.ufpel.fia.exaustiva.util.Element;
import java.util.ArrayList;
import java.util.List;
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

    public List<BoardState> run() {
        Stack<BoardState> pilha = new Stack<>();
        List<BoardState> solucao = new ArrayList<>();
        pilha.add(beginState);
        while (!pilha.isEmpty()) {
            if (pilha.size() > this.collectionLimit) {
                System.out.printf("Falha! Collection com muitos elementos[%s]\n ", pilha.size());
                throw new RuntimeException("Collection com muitos elementos");
            }
            BoardState tempState = pilha.pop();
            solucao.add(tempState);
//            this.board.print(tempState);   // informações parciais
            if (!this.board.isTheSolution(tempState)) {
                Element[] findCandidates = this.board.findCandidates(tempState, isShuffle);
                for (Element possibilidade : findCandidates) {
                    BoardState move = this.board.move(possibilidade, tempState);
                    move.setHeight(tempState.getHeight()+1);
                    pilha.add(move);
                }
            } else {
                System.out.printf("Fim com sucesso! Elementos restantes na Collection[%d]\n", pilha.size());
                return solucao;
            }
        }
        System.out.printf("Falha! Não encontrou solução\n ");
        throw new RuntimeException("Falha! Não encontrou solução");
    }
}
