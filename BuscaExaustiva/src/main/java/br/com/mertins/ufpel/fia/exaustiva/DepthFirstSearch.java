package br.com.mertins.ufpel.fia.exaustiva;

import br.com.mertins.ufpel.fia.util.BasicSearch;
import br.com.mertins.ufpel.fia.util.BoardState;
import br.com.mertins.ufpel.fia.util.Element;
import br.com.mertins.ufpel.fia.util.Observator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Busca em profundidade no quebra-cabeça deslizante
 *
 * @author mertins
 */
public class DepthFirstSearch extends BasicSearch {

    private final int limitRamo;

    /**
     * Construtor para realizar busca em profundidade no quebra-cabeça
     * deslizante
     *
     * @param observator classe responsável em receber as informações que
     * servirão ao relatório
     * @param isShuffle embaralhar qual candidato é visitável primeiro (reduz a
     * repetição de ir e vir da mesma peça)
     * @param withHash usa tabela de hash para evitar movimentos já avaliados
     */
    public DepthFirstSearch(Observator observator, boolean isShuffle, boolean withHash) {
        super(observator, isShuffle, withHash);
        this.limitRamo = observator.getShuffle();// * 10000;
    }

    @Override
    public List<BoardState> run() {
        Stack<BoardState> pilha = new Stack<>();
        pilha.add(beginState);
        int nivel = 0;
        long countTrocaRamo = 0;
        long hashColision = 0;
        try {
            while (!pilha.isEmpty()) {
                if (observator.isTimeOver()) {
                    observator.errSolution(nivel);
                    throw new RuntimeException(String.format("Tempo excedido! Nivel atingido [%s]", nivel));
                }
                BoardState testState = pilha.pop();
//                this.board.print(testState);   // informações parciais
                if (!this.board.isTheSolution(testState)) {
                    nivel = testState.getHeight() + 1;
                    if (nivel <= limitRamo) {
                        Element[] findCandidates = this.board.findCandidates(testState, isShuffle);
                        for (Element possibilidade : findCandidates) {
                            BoardState move = this.board.move(possibilidade, testState);
                            if (!withHash || !this.hashTable.contains(move)) {
                                nivel = testState.getHeight() + 1;
                                move.setHeight(nivel);
                                move.setFather(testState);
                                if (withHash) {
                                    this.hashTable.add(move);
                                }
                                pilha.add(move);
                            } else {
                                observator.setHashColision(hashColision++);
                            }
                        }
                    } else {
                        observator.setChangePath(countTrocaRamo++);
                        observator.setHeight(nivel);
                    }
                } else {
                    observator.okSolution();
                    pilha.clear();
                    return this.makeSolution(testState);
                }
            }
        } catch (OutOfMemoryError ex) {
            observator.errSolution(nivel);
            pilha.clear();
            throw new RuntimeException(String.format("Memory full! Nivel atingido [%s]", nivel));
        }
        observator.errSolution(nivel);
        throw new RuntimeException("Falha! Não encontrou solução e Collection vazia");
    }
}
