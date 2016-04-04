package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.BasicSearch;
import br.com.mm.ufpel.fia.exaustiva.util.BoardState;
import br.com.mm.ufpel.fia.exaustiva.util.Element;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Busca em amplitude no quebra-cabeça deslizante
 *
 * @author mertins
 */
public class BreadthFirstSearch extends BasicSearch {

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
        super(size, shuffle, collectionLimit, isShuffle);
    }

    public List<BoardState> run() {
        Queue<BoardState> lista = new LinkedList<>();
        lista.add(beginState);
        while (!lista.isEmpty()) {
            if (lista.size() > this.collectionLimit) {
                System.out.printf("Falha! Collection com muitos elementos[%s]\n ", lista.size());
                throw new RuntimeException("Collection com muitos elementos");
            }
            BoardState testState = lista.poll();
//            this.board.print(testState);    // informações parciais
            if (!this.board.isTheSolution(testState)) {
                Element[] findCandidates = this.board.findCandidates(testState, isShuffle);
                for (Element possibilidade : findCandidates) {
                    BoardState move = this.board.move(possibilidade, testState);
                    move.setHeight(testState.getHeight() + 1);
                    move.setFather(testState);
                    lista.add(move);
                }
            } else {
                System.out.printf("Fim com sucesso! Elementos restantes na Collection[%d]\n", lista.size());
                return this.makeSolution(testState);
            }
        }
        System.out.printf("Falha! Não encontrou solução\n ");
        throw new RuntimeException("Falha! Não encontrou solução");
    }

    private List<BoardState> makeSolution(BoardState estadoFinal) {
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
