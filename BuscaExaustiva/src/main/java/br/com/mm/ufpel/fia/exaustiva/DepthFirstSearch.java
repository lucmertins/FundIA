package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.BasicSearch;
import br.com.mm.ufpel.fia.exaustiva.util.BoardState;
import br.com.mm.ufpel.fia.exaustiva.util.Element;
import java.util.List;
import java.util.Stack;

/**
 * Busca em profundidade no quebra-cabeça deslizante
 *
 * @author mertins
 */
public class DepthFirstSearch extends BasicSearch {

    /**
     * Construtor para realizar busca em profundidade no quebra-cabeça
     * deslizante
     *
     * @param size tamanho do tabuleiro
     * @param shuffle quantidade de embaralhamento das peças
     * @param isShuffle embaralhar qual candidato é visitável primeiro (reduz a
     * repetição de ir e vir da mesma peça)
     *
     */
    public DepthFirstSearch(int size, int shuffle, boolean isShuffle) {
        super(size, shuffle, isShuffle);
    }

    @Override
    public List<BoardState> run() {
        Stack<BoardState> pilha = new Stack<>();
        pilha.add(beginState);
        int nivel = 0;
        try {
            while (!pilha.isEmpty()) {
                BoardState testState = pilha.pop();
//            this.board.print(tempState);   // informações parciais
                if (!this.board.isTheSolution(testState)) {
                    Element[] findCandidates = this.board.findCandidates(testState, isShuffle);
                    for (Element possibilidade : findCandidates) {
                        BoardState move = this.board.move(possibilidade, testState);
                        nivel = testState.getHeight() + 1;
                        move.setHeight(nivel);
                        move.setFather(testState);
                        pilha.add(move);
                    }
                } else {
                    pilha.clear();
                    return this.makeSolution(testState);
                }
            }
        } catch (OutOfMemoryError ex) {
            long totalMemoria = Runtime.getRuntime().totalMemory() / 1048576;
            long livreMemoria = Runtime.getRuntime().freeMemory() / 1048576;
            pilha.clear();
            throw new RuntimeException(String.format("Memory full! Nivel atingido [%s]       Memória disponível [%dM]      Livre [%dM]", nivel, totalMemoria, livreMemoria));
        }
        throw new RuntimeException("Falha! Não encontrou solução e Collection vazia");
    }
}
